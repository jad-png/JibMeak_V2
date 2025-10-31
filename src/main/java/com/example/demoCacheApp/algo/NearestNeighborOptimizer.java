package com.taxist.JibMeak.algo;

import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.Tour;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.model.Warehouse;
import com.taxist.JibMeak.model.enums.VehicleType;
import com.taxist.JibMeak.utils.DistanceCalculator;
import com.taxist.JibMeak.utils.VehicleConstraints;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NearestNeighborOptimizer implements Optimizer {
    public Tour optimizeTour(Warehouse wh, List<Delivery> allDvs, Vehicle vh) {
        // step1: filter by capacity
        List<Delivery> feasibleDvs = filterByCapacity(allDvs, vh);

        // sterp 2: condition if no dvs fit
        if (feasibleDvs.isEmpty()) {
            return createEmptyTour(wh, vh);

        }

        // step 3; apply algo
        List<Delivery> optimizedRoute = applyAlgo(wh, feasibleDvs);

        // step 4: create tour
        return createTourFromRoute(wh, optimizedRoute, vh);
    }

    private List<Delivery> filterByCapacity(List<Delivery> dvs, Vehicle vh) {
        List<Delivery> feasible = new ArrayList<>();

        double currentWeight = 0.0;
        double currentVolume = 0.0;
        int currentCount = 0;

        VehicleType vhType = vh.getType();

        double maxWeight = VehicleConstraints.getMaxWeight(vhType);
        double maxVolume = VehicleConstraints.getMaxVolume(vhType);
        double maxDeliveries = VehicleConstraints.getMaxDeliveries(vhType);

        for (Delivery dv : dvs) {
            boolean weightOk = (currentWeight + dv.getWeightKg()) <= maxWeight;
            boolean volumeOk = (currentVolume + dv.getVolumeM3()) <= maxVolume;
            boolean countOk = (currentCount + 1) <= maxDeliveries;

            if (weightOk && volumeOk && countOk) {
                feasible.add(dv);
                currentWeight += dv.getWeightKg();
                currentVolume += dv.getVolumeM3();
                currentCount++;
            }
        }
        return feasible;
    }

    private List<Delivery> applyAlgo(Warehouse wh, List<Delivery> dvs) {
        List<Delivery> unvisited =  new ArrayList<>(dvs);
        List<Delivery> tourRoute = new ArrayList<>();

        // track current position using last dv coordinates
        // start with wh as curr position
        double currentLat = wh.getLatitude();
        double currentLon = wh.getLongitude();

        while (!unvisited.isEmpty()) {
            Delivery closest = findClosestDelivery(currentLat, currentLon, unvisited);

            tourRoute.add(closest);
            unvisited.remove(closest);

            // update curr coordinates
            currentLat = closest.getLatitude();
            currentLon = closest.getLongitude();
        }

        return tourRoute;
    }

    private Delivery findClosestDelivery(double currentLat, double currentLon, List<Delivery> dvs) {
        Delivery closest = null;
        double minDistance = Double.MAX_VALUE;

        // calculate distance between curr loc and next delivery coordinates
        for (Delivery dv : dvs) {
            double d = DistanceCalculator.calculateDistance(
                    currentLat, currentLon,
                    dv.getLatitude(), dv.getLongitude()
            );

            if (d < minDistance) {
                minDistance = d;
                closest = dv;
            }
        }
        return closest;
    }

    private Tour createTourFromRoute(Warehouse warehouse, List<Delivery> route, Vehicle vehicle) {
        Tour tour = new Tour();
        tour.setWarehouse(warehouse);
        tour.setVehicle(vehicle);
        tour.setDeliveries(route);

        // Calculate and log total distance
        double totalDistance = calculateTotalDistance(warehouse, route);
        return tour;
    }

    private Tour createEmptyTour(Warehouse warehouse, Vehicle vehicle) {
        Tour tour = new Tour();
        tour.setWarehouse(warehouse);
        tour.setVehicle(vehicle);
        tour.setDate(LocalDate.now());
        tour.setDeliveries(new ArrayList<>()); // Empty list
        return tour;
    }

    private double calculateTotalDistance(Warehouse wh, List<Delivery> route) {
        if (route.isEmpty()) return 0.0;

        double totalDistance = 0.0;

        // Distance from warehouse to first delivery
        Delivery first = route.get(0);
        totalDistance += DistanceCalculator.calculateDistance(wh, first);

        // Distances between consecutive dvs
        for (int i = 0; i < route.size() - 1; i++) {
            Delivery current = route.get(i);
            Delivery next = route.get(i + 1);
            totalDistance += DistanceCalculator.calculateDistance(current, next);
        }

        // Distance from last dv back to wh
        Delivery last = route.get(route.size() - 1);
        totalDistance += DistanceCalculator.calculateDistance(last, wh);

        return totalDistance;
    }
}
