package com.taxist.JibMeakV2.dto;

import java.time.LocalDate;
import java.util.List;

public class TourOptimizationDTO {
    private Long id;
    private LocalDate date;
    private Long warehouseId;
    private Long vehicleId;
    private List<Long> deliveryIds;

    private String status;

    // Constructor, getters, setters
    public TourOptimizationDTO() {}

    public TourOptimizationDTO(Long warehouseId, Long vehicleId, List<Long> deliveryIds) {
        this.warehouseId = warehouseId;
        this.vehicleId = vehicleId;
        this.deliveryIds = deliveryIds;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long warehouseId) { this.warehouseId = warehouseId; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public List<Long> getDeliveryIds() { return deliveryIds; }
    public void setDeliveryIds(List<Long> deliveryIds) { this.deliveryIds = deliveryIds; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
