package com.taxist.JibMeakV2.model;

import com.taxist.JibMeakV2.model.enums.VehicleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "max_weight_kg")
    private double maxWeightKg;

    @Column(name = "max_volume_m3")
    private double maxVolumeM3;

    @Column(name = "max_deliveries")
    private int maxDeliveries;

    @OneToMany(mappedBy = "vehicle")
    private List<Tour> tours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public double getMaxWeightKg() {
        return maxWeightKg;
    }

    public void setMaxWeightKg(double maxWeightKg) {
        this.maxWeightKg = maxWeightKg;
    }

    public double getMaxVolumeM3() {
        return maxVolumeM3;
    }

    public void setMaxVolumeM3(double maxVolumeM3) {
        this.maxVolumeM3 = maxVolumeM3;
    }

    public int getMaxDeliveries() {
        return maxDeliveries;
    }

    public void setMaxDeliveries(int maxDeliveries) {
        this.maxDeliveries = maxDeliveries;
    }

    public List<Tour> getTours() { return tours; }

    public void setTours(List<Tour> tours) { this.tours = tours; }
}
