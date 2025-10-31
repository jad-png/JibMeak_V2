package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.Warehouse;

import java.time.LocalTime;

public class WarehouseValidationUtils {

    private WarehouseValidationUtils() {
        // Utility class - prevent instantiation
    }

    public static void validateWarehouse(Warehouse warehouse) {
        validateAddress(warehouse.getAddress());
        validateCoordinates(warehouse.getLatitude(), warehouse.getLongitude());
        validateOperatingHours(warehouse.getOpenHour(), warehouse.getCloseHour());
    }

    public static void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse address is required");
        }
        if (address.length() < 5) {
            throw new IllegalArgumentException("Warehouse address must be at least 5 characters long");
        }
        if (address.length() > 255) {
            throw new IllegalArgumentException("Warehouse address cannot exceed 255 characters");
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

    public static void validateOperatingHours(LocalTime openHour, LocalTime closeHour) {
        if (openHour == null || closeHour == null) {
            throw new IllegalArgumentException("Warehouse operating hours are required");
        }

        if (openHour.isAfter(closeHour)) {
            throw new IllegalArgumentException("Warehouse open hour must be before close hour");
        }

        // Minimum operating hours (e.g., at least 4 hours)
        if (openHour.plusHours(4).isAfter(closeHour)) {
            throw new IllegalArgumentException("Warehouse must be open for at least 4 hours");
        }

        // Reasonable business hours
        LocalTime earliestOpen = LocalTime.of(5, 0); // 5 AM
        LocalTime latestClose = LocalTime.of(23, 0); // 11 PM

        if (openHour.isBefore(earliestOpen)) {
            throw new IllegalArgumentException("Warehouse cannot open before 5:00 AM");
        }
        if (closeHour.isAfter(latestClose)) {
            throw new IllegalArgumentException("Warehouse cannot close after 11:00 PM");
        }
    }

    public static void validateOperatingHoursUpdate(LocalTime currentOpen, LocalTime currentClose,
                                                    LocalTime newOpen, LocalTime newClose) {
        if (!currentOpen.equals(newOpen) || !currentClose.equals(newClose)) {
            validateOperatingHours(newOpen, newClose);
        }
    }

    public static void validateDeletion(Warehouse warehouse) {
        if (warehouse.getTours() != null && !warehouse.getTours().isEmpty()) {
            throw new IllegalStateException("Cannot delete warehouse that is assigned to tours");
        }
    }
}