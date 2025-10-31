package com.taxist.JibMeak.dto;

import java.util.List;

public class TourOptimizationDTO {
    private Long warehouseId;
    private Long vehicleId;
    private List<Long> deliveryIds;

    // Constructor, getters, setters
    public TourOptimizationDTO() {}

    public TourOptimizationDTO(Long warehouseId, Long vehicleId, List<Long> deliveryIds) {
        this.warehouseId = warehouseId;
        this.vehicleId = vehicleId;
        this.deliveryIds = deliveryIds;
    }

    // Getters and setters
    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long warehouseId) { this.warehouseId = warehouseId; }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public List<Long> getDeliveryIds() { return deliveryIds; }
    public void setDeliveryIds(List<Long> deliveryIds) { this.deliveryIds = deliveryIds; }
}
