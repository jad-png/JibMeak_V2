package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.enums.VehicleType;

public class VehicleConstraints {
    public static double getMaxWeight(VehicleType type) {
        switch (type) {
            case BIKE: return 50.0;      // 50kg
            case VAN: return 1000.0;     // 1000kg
            case TRUCK: return 5000.0;   // 5000kg
            default: return 0.0;
        }
    }

    public static double getMaxVolume(VehicleType type) {
        switch (type) {
            case BIKE: return 0.5;       // 0.5 m³
            case VAN: return 8.0;        // 8 m³
            case TRUCK: return 40.0;     // 40 m³
            default: return 0.0;
        }
    }

    public static int getMaxDeliveries(VehicleType type) {
        switch (type) {
            case BIKE: return 15;        // 15 deliveries
            case VAN: return 50;         // 50 deliveries
            case TRUCK: return 100;      // 100 deliveries
            default: return 0;
        }
    }
}
