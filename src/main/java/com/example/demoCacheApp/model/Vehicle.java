package com.taxist.JibMeak.model;

import com.taxist.JibMeak.model.enums.VehicleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private double maxWeightKg;
    private double maxVolumeM3;

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
