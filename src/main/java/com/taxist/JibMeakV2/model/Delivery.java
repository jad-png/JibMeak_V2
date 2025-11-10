package com.taxist.JibMeakV2.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight_kg")
    private double weightKg;

    @Column(name = "volume_m3")
    private double volumeM3;

    @Column(name = "preferred_window_start")
    private LocalTime preferredWindowStart;

    @Column(name = "preferred_window_end")
    private LocalTime preferredWindowEnd;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPreferredWindowStart(LocalTime preferredWindowStart) { this.preferredWindowStart = preferredWindowStart; }

    public LocalTime getPreferredWindowEnd() {
        return preferredWindowEnd;
    }

    public void setPreferredWindowEnd(LocalTime preferredWindowEnd) {
        this.preferredWindowEnd = preferredWindowEnd;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}

