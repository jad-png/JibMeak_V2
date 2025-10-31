package com.taxist.JibMeak.dto;

import com.taxist.JibMeak.model.enums.DeliveryStatus;

import java.time.LocalTime;

public class DeliveryDTO {
    private Long id;
    private double latitude;
    private double longitude;
    private double weightKg;
    private double volumeM3;
    private LocalTime preferredWindowStart;
    private LocalTime preferredWindowEnd;
    private DeliveryStatus status;
    private Long tourId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }

    public double getVolumeM3() { return volumeM3; }
    public void setVolumeM3(double volumeM3) { this.volumeM3 = volumeM3; }

    public LocalTime getPreferredWindowStart() { return preferredWindowStart; }
    public void setPreferredWindowStart(LocalTime preferredWindowStart) { this.preferredWindowStart = preferredWindowStart; }

    public LocalTime getPreferredWindowEnd() { return preferredWindowEnd; }
    public void setPreferredWindowEnd(LocalTime preferredWindowEnd) { this.preferredWindowEnd = preferredWindowEnd; }

    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }
}
