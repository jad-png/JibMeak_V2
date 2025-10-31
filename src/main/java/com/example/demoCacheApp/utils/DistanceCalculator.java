package com.taxist.JibMeak.utils;

import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.Warehouse;

public class DistanceCalculator {

    // distance between two dvs
    public static double calculateDistance(Delivery dv1, Delivery dv2) {
        return calculateDistance(
                dv1.getLatitude(), dv1.getLongitude(),
                dv2.getLatitude(), dv2.getLongitude()
        );
    }

    // distance between wh and dv
    public static double calculateDistance(Warehouse wh, Delivery dv) {
        return calculateDistance(
                wh.getLatitude(), wh.getLongitude(),
                dv.getLatitude(), dv.getLongitude()
        );
    }

    // distance between last dv and wh
    public static double calculateDistance(Delivery dv, Warehouse wh) {
        return calculateDistance(
                dv.getLatitude(), dv.getLongitude(),
                wh.getLatitude(), wh.getLongitude()
        );
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double EARTH_RADIUS_KM = 6371.0;

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);

        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return  EARTH_RADIUS_KM * c; // distance in km
    }
}
