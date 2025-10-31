package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.model.enums.VehicleType;

public class VehicleValidationUtils {

    private VehicleValidationUtils() {
        // Utility class - prevent instantiation
    }

    public static void validateVehicle(Vehicle vehicle) {
        validateType(vehicle.getType());
        validateWeightCapacity(vehicle.getMaxWeightKg());
        validateVolumeCapacity(vehicle.getMaxVolumeM3());
        validateDeliveryCapacity(vehicle.getMaxDeliveries());
        validateCapacityRatios(vehicle);
    }

    public static void validateType(VehicleType type) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type is required");
        }
    }

    public static void validateWeightCapacity(double maxWeightKg) {
        if (maxWeightKg <= 0) {
            throw new IllegalArgumentException("Vehicle maximum weight must be greater than 0");
        }
        if (maxWeightKg > 50000) { // 50 tons max
            throw new IllegalArgumentException("Vehicle maximum weight cannot exceed 50,000 kg");
        }
    }

    public static void validateVolumeCapacity(double maxVolumeM3) {
        if (maxVolumeM3 <= 0) {
            throw new IllegalArgumentException("Vehicle maximum volume must be greater than 0");
        }
        if (maxVolumeM3 > 200) { // 200 m³ max
            throw new IllegalArgumentException("Vehicle maximum volume cannot exceed 200 m³");
        }
    }

    public static void validateDeliveryCapacity(int maxDeliveries) {
        if (maxDeliveries <= 0) {
            throw new IllegalArgumentException("Vehicle maximum deliveries must be greater than 0");
        }
        if (maxDeliveries > 100) { // 100 deliveries max
            throw new IllegalArgumentException("Vehicle maximum deliveries cannot exceed 100");
        }
    }

    public static void validateCapacityRatios(Vehicle vehicle) {
        double weightPerDelivery = vehicle.getMaxWeightKg() / vehicle.getMaxDeliveries();
        double volumePerDelivery = vehicle.getMaxVolumeM3() / vehicle.getMaxDeliveries();

        if (weightPerDelivery < 10) { // At least 10kg per delivery
            throw new IllegalArgumentException("Vehicle capacity ratio is unreasonable: too many deliveries for weight capacity");
        }

        if (volumePerDelivery < 0.1) { // At least 0.1 m³ per delivery
            throw new IllegalArgumentException("Vehicle capacity ratio is unreasonable: too many deliveries for volume capacity");
        }
    }

    public static void validateVehicleUpdates(Vehicle existingVehicle, Vehicle updatedVehicle) {
        if (updatedVehicle.getMaxWeightKg() < existingVehicle.getMaxWeightKg()) {
            throw new IllegalArgumentException("Cannot reduce vehicle weight capacity");
        }
        if (updatedVehicle.getMaxVolumeM3() < existingVehicle.getMaxVolumeM3()) {
            throw new IllegalArgumentException("Cannot reduce vehicle volume capacity");
        }
        if (updatedVehicle.getMaxDeliveries() < existingVehicle.getMaxDeliveries()) {
            throw new IllegalArgumentException("Cannot reduce vehicle delivery capacity");
        }
    }

    public static void validateDeletion(Vehicle vehicle) {
        if (vehicle.getTours() != null && !vehicle.getTours().isEmpty()) {
            throw new IllegalStateException("Cannot delete vehicle that is assigned to tours");
        }
    }
}