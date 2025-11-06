package com.taxist.JibMeakV2.service.interfaces;


import com.taxist.JibMeakV2.dto.TourDTO;
import com.taxist.JibMeakV2.dto.TourOptimizationDTO;

import java.util.List;

public interface TourService {
    TourDTO completeTour(Long tourId);

    TourDTO getTourById(Long id);

    List<TourDTO> getAllTours();

    void deleteTour(Long id);

    TourDTO createOptimizedTour(TourOptimizationDTO request);

//    TourStatisticsDTO getTourByVehicleIdAndDate(Long vehicleId, LocalDate date);
}
