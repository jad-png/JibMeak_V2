package com.taxist.JibMeak.model;

import com.taxist.JibMeak.model.enums.DeliveryStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    private double weightKg;
    private double volumeM3;

    private LocalTime preferredWindowStart;
    private LocalTime preferredWindowEnd;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status = DeliveryStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(double volumeM3) {
        this.volumeM3 = volumeM3;
    }

    public LocalTime getPreferredWindowStart() {
        return preferredWindowStart;
    }

    public void setPreferredWindowStart(LocalTime preferredWindowStart) {
        this.preferredWindowStart = preferredWindowStart;
    }

    public LocalTime getPreferredWindowEnd() {
        return preferredWindowEnd;
    }

    public void setPreferredWindowEnd(LocalTime preferredWindowEnd) {
        this.preferredWindowEnd = preferredWindowEnd;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}

