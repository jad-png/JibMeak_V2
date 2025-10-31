package com.taxist.JibMeak.dto;

import java.util.List;

public class TourStatisticsDTO {
    private List<TourDTO> tours;
    private double totalDistanceKm;

    public List<TourDTO> getTours() { return tours; }
    public void setTours(List<TourDTO> tours) { this.tours = tours; }
    public double getTotalDistanceKm() { return totalDistanceKm; }
    public void setTotalDistanceKm(double totalDistanceKm) { this.totalDistanceKm = totalDistanceKm; }
}
