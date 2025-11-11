package com.taxist.JibMeakV2.algo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxist.JibMeakV2.mapper.DeliveryHistoryMapper;
import com.taxist.JibMeakV2.model.*;
import com.taxist.JibMeakV2.repository.DeliveryHistoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("AIOptimizer")
@ConditionalOnProperty(prefix = "app.optimizer", name = "active", havingValue = "ai")
public class AIOptimizer implements Optimizer {
    private final ChatClient chatClient;
    private final DeliveryHistoryRepository repo;
    private final ObjectMapper mapper;

    public AIOptimizer(ChatClient.Builder chatClient, DeliveryHistoryRepository repo, ObjectMapper mapper) {
        this.chatClient = chatClient.build();
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public Tour optimizeTour(Warehouse wh, List<Delivery> allDvs, Vehicle vh) {
        System.out.println("--- AI MODEL IS RUNNING ---");
        List<Long> customerIds = allDvs.stream().map(d -> d.getCustomer().getId()).collect(Collectors.toList());

        List<DeliveryHistory> history = repo.findByCostumerIdIn(customerIds);


        // build json prompt
        String prompt = buildJsonPrompt(wh, allDvs, vh, history);

        // call the AI
        String aiJsonResponse = this.chatClient.prompt().user(prompt).call().content();

        // parse ai's json response

        return createTourFromAiResponse(aiJsonResponse, wh, allDvs, vh);
    }

    private String buildJsonPrompt(Warehouse wh, List<Delivery> dvs, Vehicle vh, List<DeliveryHistory> history) {
        Map<String, Object> warehouseData = Map.of(
                "address", wh.getAddress(),
                "lat", wh.getLatitude(),
                "lon", wh.getLongitude()
        );

        Map<String, Object> vehicleData = Map.of(
                "type", vh.getType(),
                "capacityWeight", vh.getMaxWeightKg(),
                "capacityVolume", vh.getMaxVolumeM3()
        );

        List<Map<String, Object>> deliveryData = dvs.stream()
                .map(d -> Map.of(
                        "id", (Object) d.getId(),
                        "customerName", d.getCustomer().getName(),
                        "address", d.getCustomer().getAddress(),
                        "timeWindow", d.getPreferredWindowStart(),
                        "weight", d.getWeightKg()
                )).toList();

        List<Map<String, Object>> historyData = history.stream()
                .map(h -> Map.of(
                        "customerId", (Object) h.getCustomer().getId(),
                        "day", h.getDayOfWeek(),
                        "delayMinutes", h.getDelayInMinutes()
                )).toList();

        Map<String, Object> prompt = new HashMap<>();
        prompt.put("warehouseData", warehouseData);
        prompt.put("vehicleData", vehicleData);
        prompt.put("deliveryData", deliveryData);
        prompt.put("historyData", historyData);

        String jsonData;
        try {
            jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prompt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error creating JSON prompt", e);
        }

        String promptFormat = String.format(
                "You are the world's most experienced logistics and delivery routing expert. " +
                        "You have studied every known algorithm and best practice in route optimization, " +
                        "including cost, time, and vehicle efficiency factors. " +
                        "Your task is to analyze the provided delivery data from the REQUEST " +
                        "and determine the most optimized route that minimizes total expenses — including fuel consumption, " +
                        "vehicle maintenance, and driver time — to maximize delivery efficiency and reduce company costs. " +

                        "You must respond strictly in JSON format with a single root object representing the optimized route plan. " +
                        "Your JSON must include the following keys:\n" +
                        " - id: (integer) the unique route ID\n" +
                        " - date: (string, yyyy-MM-dd)\n" +
                        " - vehicleId: (integer)\n" +
                        " - warehouseId: (integer)\n" +
                        " - deliveryIds: (array of integers, ordered by optimized sequence)\n" +
                        " - status: (string, e.g. 'PENDING' or 'COMPLETED')\n\n" +

                        "Example of a valid full JSON response:\n" +
                        "{\n" +
                        "  \"id\": 1,\n" +
                        "  \"date\": \"2025-11-07\",\n" +
                        "  \"vehicleId\": 1,\n" +
                        "  \"warehouseId\": 1,\n" +
                        "  \"deliveryIds\": [3, 5, 1, 2],\n" +
                        "  \"status\": \"PENDING\"\n" +
                        "}\n\n" +

                        "Rules:\n" +
                        "- Output only valid JSON.\n" +
                        "- Do not include explanations or text outside the JSON.\n" +
                        "- Ensure deliveryIds are sorted in the most cost-efficient order."
        );
        return promptFormat;
    }

    private Tour createTourFromAiResponse(String aiResponse, Warehouse wh, List<Delivery> dvs, Vehicle vh) {
        try {
            Map<String, List<Long>> aiResponseMap = mapper.readValue(aiResponse, Map.class);
            List<Long> orderedIds = aiResponseMap.get("key-here");

            List<Delivery> optimizedRoute = orderedIds.stream().map(
                    id -> dvs.stream()
                            .filter(d -> d.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Ai return unknow for ID: " + id)))
                    .collect(Collectors.toList());

            Tour tour = new Tour();
            tour.setWarehouse(wh);
            tour.setVehicle(vh);
            tour.setDeliveries(optimizedRoute);
            tour.setDate(LocalDate.now());
            for (Delivery d : optimizedRoute) {
                d.setTour(tour);
            }
            return tour;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing AI JSON response", e);
        }
    }
}
