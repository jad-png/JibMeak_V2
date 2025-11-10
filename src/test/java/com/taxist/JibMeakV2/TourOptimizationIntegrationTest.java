package com.taxist.JibMeakV2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.model.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TourOptimizationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestDataFactory testDataFactory;

    private Warehouse testWarehouse;
    private Vehicle testVehicle;
    private LocalDate today = LocalDate.now();

    @BeforeEach
    void setUp() {
        testDataFactory.cleanDatabase(); // Clean up

        // Create test data
        testWarehouse = testDataFactory.createAndSaveWarehouse();
        testVehicle = testDataFactory.createAndSaveVehicle();

        Customer c1 = testDataFactory.createAndSaveCustomer("Alice", "Wonderland");
        Customer c2 = testDataFactory.createAndSaveCustomer("Bob", "Builder's Yard");

        // Create deliveries for today
        testDataFactory.createAndSaveDelivery(c1, today);
        testDataFactory.createAndSaveDelivery(c2, today);

        // Create deliveries for another day (to make sure they are filtered out)
        testDataFactory.createAndSaveDelivery(c1, today.plusDays(1));
    }

    @Test
    void testOptimizeTourEndpoint() throws Exception {
        Map<String, Object> optimizationRequest = new HashMap<>();
        optimizationRequest.put("warehouseId", testWarehouse.getId());
        optimizationRequest.put("vehicleId", testVehicle.getId());
        optimizationRequest.put("date", today); // We are optimizing for today

        mockMvc.perform(post("/api/tours/optimize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(optimizationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tour").exists())
                .andExpect(jsonPath("$.tour.vehicle.id").value(testVehicle.getId()))
                .andExpect(jsonPath("$.tour.warehouse.id").value(testWarehouse.getId()))
                .andExpect(jsonPath("$.tour.deliveries.length()").value(2));
    }
}
