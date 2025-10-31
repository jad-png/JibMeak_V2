package com.taxist.JibMeak.dto;

import com.taxist.JibMeak.model.enums.VehicleType;

import java.util.List;

public class VehicleDTO {
    private Long id;
    private VehicleType type;
    private double maxWeightKg;
    private double maxVolumeM3;
    private int maxDeliveries;

    private List<Long> tourIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }

    public double getMaxWeightKg() { return maxWeightKg; }
    public void setMaxWeightKg(double maxWeightKg) { this.maxWeightKg = maxWeightKg; }

    public double getMaxVolumeM3() { return maxVolumeM3; }
    public void setMaxVolumeM3(double maxVolumeM3) { this.maxVolumeM3 = maxVolumeM3; }

    public int getMaxDeliveries() { return maxDeliveries; }
    public void setMaxDeliveries(int maxDeliveries) { this.maxDeliveries = maxDeliveries; }
}
