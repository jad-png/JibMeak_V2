package com.taxist.JibMeakV2.service.Impl;


import com.taxist.JibMeakV2.algo.Optimizer;
import com.taxist.JibMeakV2.dto.TourDTO;
import com.taxist.JibMeakV2.dto.TourOptimizationDTO;
import com.taxist.JibMeakV2.mapper.TourMapper;
import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.model.Warehouse;
import com.taxist.JibMeakV2.repository.DeliveryRepository;
import com.taxist.JibMeakV2.repository.TourRepository;
import com.taxist.JibMeakV2.repository.VehicleRepository;
import com.taxist.JibMeakV2.repository.WarehouseRepository;
import com.taxist.JibMeakV2.service.interfaces.TourService;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final WarehouseRepository whRepo;
    private final VehicleRepository vehRepo;
    private final DeliveryRepository dvRepo;
    private final Optimizer optimizer;

    public TourServiceImpl(TourRepository trRepository, TourMapper trMapper, WarehouseRepository whRepo,
                           DeliveryRepository dvRepo, VehicleRepository vhRepo,
                           @Qualifier("NearestNeighbor") Optimizer optimizer) {
        this.tourRepository = trRepository;
        this.tourMapper = trMapper;
        this.whRepo = whRepo;
        this.dvRepo = dvRepo;
        this.vehRepo = vhRepo;
        this.optimizer = optimizer;
    }

    @Override
    public TourDTO createTour(TourDTO dto) {
        Tour tour = tourMapper.toEntity(dto);
        
        validateTour(tour);
        Tour savedTour = tourRepository.save(tour);
        return tourMapper.toDTO(savedTour);
    }

    @Override
    public TourDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tour not dound"));
        return tourMapper.toDTO(tour);
    }

    @Override
    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream().map(tourMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    private void validateTour(Tour tour) {
        if (tour.getDate() == null) {
            throw new RuntimeException("tour date is required");
        }
    }

    public TourDTO createOptimizedTour(TourOptimizationDTO request) {

        Warehouse wh = whRepo.findById(request.getWarehouseId())
                .orElseThrow(() -> new  RuntimeException("warehouse not found"));

        Vehicle vh = vehRepo.findById(request.getVehicleId())
                .orElseThrow(() -> new  RuntimeException("vehicle not found"));

        List<Delivery> dvs = dvRepo.findAllById(request.getDeliveryIds());

        Tour optimizedTour = optimizer.optimizeTour(wh, dvs, vh);

        Tour savedTour = tourRepository.save(optimizedTour);

        return tourMapper.toDTO(savedTour);
    }

//    private double calculateTourDistance(Tour t) {
//        double distance = 0.0;
//
//        List<Delivery> dvs = t.getDeliveries();
//        Warehouse wh = t.getWarehouse();
//
//        // step 1: dsitance from wh to delivery 1
//        distance += DistanceCalculator.calculateDistance(wh, dvs.get(0));
//
//        // step 2: distance between intermediate deliveries
//        for (int i = 1; i < t.getDeliveries().size(); i++) {
//            distance += DistanceCalculator.calculateDistance(dvs.get(i), dvs.get(i + 1));
//        }
//
//        // step 3: distance from last delivery wayback to warehouse
//        distance += DistanceCalculator.calculateDistance(dvs.get(dvs.size() -1), wh);
//
//        return distance;
//    }
//
//    public TourStatisticsDTO getTourByVehicleIdAndDate(Long vehicleId, LocalDate date) {
//        List<Tour> trs = tourRepository.findTourByVehicleIdAndDate(vehicleId, date);
//
//        double distance = trs.stream().mapToDouble(this::calculateTourDistance).sum();
//
//        List<TourDTO> tourDTOs = trs.stream().map(tourMapper::toDTO).collect(Collectors.toList());
//
//        TourStatisticsDTO dto = new TourStatisticsDTO();
//
//        dto.setTours(tourDTOs);
//        dto.setTotalDistanceKm(distance);
//
//        return dto;
//    }
}
