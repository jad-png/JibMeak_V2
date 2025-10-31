package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.Tour;

import java.time.LocalDate;

public class TourValidationUtils {
    private TourValidationUtils() {
        // Utility class - prevent instantiation
    }

    public static void validateTour(Tour tour) {
        validateDate(tour.getDate());
        validateVehicleAssignment(tour.getVehicle());
        validateWarehouseAssignment(tour.getWarehouse());

        if (tour.getDeliveries() != null) {
            validateTourDeliveries(tour);
        }
    }

    public static void validateDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Tour date is required");
        }

        LocalDate today = LocalDate.now();
        if (date.isBefore(today)) {
            throw new IllegalArgumentException("Tour date cannot be in the past");
        }

        LocalDate maxDate = today.plusYears(1);
        if (date.isAfter(maxDate)) {
            throw new IllegalArgumentException("Tour date cannot be more than 1 year in the future");
        }
    }

    public static void validateVehicleAssignment(Object vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Tour must have a vehicle assigned");
        }
    }

    public static void validateWarehouseAssignment(Object warehouse) {
        if (warehouse == null) {
            throw new IllegalArgumentException("Tour must have a warehouse assigned");
        }
    }

    public static void validateTourDeliveries(Tour tour) {
        validateDeliveryCount(tour);
        validateTotalWeight(tour);
        validateTotalVolume(tour);
    }

    private static void validateDeliveryCount(Tour tour) {
        int deliveryCount = tour.getDeliveries().size();
        int maxDeliveries = tour.getVehicle().getMaxDeliveries();

        if (deliveryCount > maxDeliveries) {
            throw new IllegalArgumentException(
                    "Number of deliveries (" + deliveryCount +
                            ") exceeds vehicle capacity (" + maxDeliveries + ")"
            );
        }
    }

    private static void validateTotalWeight(Tour tour) {
        double totalWeight = tour.getDeliveries().stream()
                .mapToDouble(delivery -> delivery != null ? delivery.getWeightKg() : 0)
                .sum();

        double maxWeight = tour.getVehicle().getMaxWeightKg();

        if (totalWeight > maxWeight) {
            throw new IllegalArgumentException(
                    "Total delivery weight (" + totalWeight +
                            " kg) exceeds vehicle capacity (" + maxWeight + " kg)"
            );
        }
    }

    private static void validateTotalVolume(Tour tour) {
        double totalVolume = tour.getDeliveries().stream()
                .mapToDouble(delivery -> delivery != null ? delivery.getVolumeM3() : 0)
                .sum();

        double maxVolume = tour.getVehicle().getMaxVolumeM3();

        if (totalVolume > maxVolume) {
            throw new IllegalArgumentException(
                    "Total delivery volume (" + totalVolume +
                            " m³) exceeds vehicle capacity (" + maxVolume + " m³)"
            );
        }
    }
}
