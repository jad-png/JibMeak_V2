package com.taxist.JibMeakV2.model;

import jakarta.persistence.*;
import org.springframework.core.annotation.Order;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    @Column(name = "preferred_time_slot")
    private String preferredTimeSlot; // e.g., "09:00-11:00"

    @OneToMany(mappedBy = "customer")
    private List<Delivery> deliveries;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double isLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double isLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getPreferredTimeSlot() { return preferredTimeSlot; }

    public List<Delivery> getDeliveries() { return deliveries; }
    public void setDeliveries(List<Delivery> deliveries) { this.deliveries = deliveries; }
}
