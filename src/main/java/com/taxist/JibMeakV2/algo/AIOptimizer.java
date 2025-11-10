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
                "You are world's MOST experienced logistics expert who has read every book on delivery routing. " +
                        "Based on delivered and provided DATA given from REQUEST." +
                        "You have to provide the most efficient route, so that the company dont lose mone on tou's vehicle expences, and time, like gasoil, vehicle maintenance, mechanic. Goal here is to reduce and decrease tour expenses." +
                        "Based on this data tour response MUST be on a JSON object with a single key {key-here}" +
                        "wich contains an array of the delivery IDs in the correct order." +
                        "Exemple full RESPONSE : {\n" +
                        "    \"id\": 1,\n" +
                        "    \"date\": \"2025-11-07\",\n" +
                        "    \"vehicleId\": 1,\n" +
                        "    \"warehouseId\": 1,\n" +
                        "    \"deliveryIds\": [\n" +
                        "        3\n" +
                        "        5\n" +
                        "        1\n" +
                        "        2\n" +
                        "    ],\n" +
                        "    \"status\": \"PENDING\"\n" +
                        "}"
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
