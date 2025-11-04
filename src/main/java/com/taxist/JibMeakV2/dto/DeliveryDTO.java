package com.taxist.JibMeakV2.dto;


import com.taxist.JibMeakV2.model.enums.DeliveryStatus;

import java.time.LocalTime;

public class DeliveryDTO {
    private Long id;
    private double weightKg;
    private double volumeM3;
    private LocalTime preferredWindowStart;
    private LocalTime preferredWindowEnd;
    private DeliveryStatus status;
    private Long tourId;
    private Long costumerId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


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

    public Long getCostumerId() { return costumerId; }
    public void setCostumerId(Long costumeId) { this.costumerId = costumeId; }
}
