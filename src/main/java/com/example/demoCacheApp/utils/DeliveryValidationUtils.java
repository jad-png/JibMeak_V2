package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.Delivery;

import java.time.LocalTime;

public class DeliveryValidationUtils {
    private DeliveryValidationUtils() {}

    public static void validateDelivery(Delivery delivery) {
        // TODO: import validation methods
        validateWeight(delivery.getWeightKg());
        validateVolume(delivery.getVolumeM3());
        validateCoordinates(delivery.getLongitude(), delivery.getLatitude());
        validateDeliveryWindow(delivery.getPreferredWindowStart(), delivery.getPreferredWindowEnd());
    }

    public static void validateWeight(double weightKg) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weight must be greater than 0");
        }

        if (weightKg > 10000) { // 10 tons max
            throw new IllegalArgumentException("Delivery weight cannot exceed 10,000 kg");
        }
    }

    public static void validateVolume(double volumeM3) {
        if (volumeM3 <= 0) {
            throw new IllegalArgumentException("Delivery volume must be greater than 0");
        }
        if (volumeM3 > 100) { // 100 m³ max
            throw new IllegalArgumentException("Delivery volume cannot exceed 100 m³");
        }
    }

    public static void validateCoordinates(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees");
        }
    }

    public static void validateDeliveryWindow(LocalTime start, LocalTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Delivery window start and end times are required");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Delivery window start time must be before end time");
        }

        // Minimum window duration (e.g., at least 1 hour)
        if (start.plusHours(1).isAfter(end)) {
            throw new IllegalArgumentException("Delivery window must be at least 1 hour long");
        }

        // Validate within reasonable business hours
        LocalTime earliestStart = LocalTime.of(6, 0); // 6 AM
        LocalTime latestEnd = LocalTime.of(22, 0);    // 10 PM

        if (start.isBefore(earliestStart)) {
            throw new IllegalArgumentException("Delivery window cannot start before 6:00 AM");
        }
        if (end.isAfter(latestEnd)) {
            throw new IllegalArgumentException("Delivery window cannot end after 10:00 PM");
        }
    }
}
