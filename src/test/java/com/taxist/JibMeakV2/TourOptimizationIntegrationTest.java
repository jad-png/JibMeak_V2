package com.taxist.JibMeakV2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxist.JibMeakV2.dto.TourOptimizationDTO;
import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.model.Delivery;
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
import java.util.List;
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
  
    private List<Long> deliveryIdsForToday;
  
    @BeforeEach
    void setUp() {
        testDataFactory.cleanDatabase(); // Clean up

        // Create test data
        testWarehouse = testDataFactory.createAndSaveWarehouse();
        testVehicle = testDataFactory.createAndSaveVehicle();

        Customer c1 = testDataFactory.createAndSaveCustomer("Alice", "Wonderland");
        Customer c2 = testDataFactory.createAndSaveCustomer("Bob", "Builder's Yard");

        Delivery d1 = testDataFactory.createAndSaveDelivery(c1, today);
        Delivery d2 = testDataFactory.createAndSaveDelivery(c2, today);

        // Save their IDs to the class field
        this.deliveryIdsForToday = List.of(d1.getId(), d2.getId());

        // This one is for another day, so we ignore it
        testDataFactory.createAndSaveDelivery(c1, today.plusDays(1));
    }

    @Test
    void testOptimizeTourEndpoint() throws Exception {

        TourOptimizationDTO optimizationRequest = new TourOptimizationDTO();
        optimizationRequest.setWarehouseId(testWarehouse.getId());
        optimizationRequest.setVehicleId(testVehicle.getId());
        optimizationRequest.setDeliveryIds(this.deliveryIdsForToday);

        mockMvc.perform(post("/api/tours/optimize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(optimizationRequest)))      .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()) // Check for any ID at the root
                .andExpect(jsonPath("$.vehicleId").value(testVehicle.getId()))
                .andExpect(jsonPath("$.warehouseId").value(testWarehouse.getId()))
                .andExpect(jsonPath("$.deliveryIds.length()").value(2));
    }
}
