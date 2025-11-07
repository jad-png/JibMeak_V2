package com.taxist.JibMeakV2.dto;

import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.model.Tour;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeliveryHistoryDTO {
    private Long id;
    private Long customerId;
    private Long tourId;

    private LocalDate deliveryDate;
    private LocalTime plannedTime;
    private LocalTime actualTime;
    private Long delayInMinutes;
    private String dayOfWeek;

    public DeliveryHistoryDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public LocalTime getPlannedTime() { return plannedTime; }
    public void setPlannedTime(LocalTime plannedTime) { this.plannedTime = plannedTime; }

    public LocalTime getActualTime() { return actualTime; }
    public void setActualTime(LocalTime actualTime) { this.actualTime = actualTime; }

    public Long getDelayInMinutes() { return delayInMinutes; }
    public void setDelayInMinutes(Long delayInMinutes) { this.delayInMinutes = delayInMinutes; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
}
