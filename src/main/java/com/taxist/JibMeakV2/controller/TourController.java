package com.taxist.JibMeakV2.controller;

import com.taxist.JibMeakV2.dto.TourDTO;
import com.taxist.JibMeakV2.dto.TourOptimizationDTO;
import com.taxist.JibMeakV2.service.interfaces.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TourDTO> addTour(@RequestBody TourDTO dto) {
        TourDTO tour = service.createTour(dto);
        return new ResponseEntity<>(tour, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourDTO> tours = service.getAllTours();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TourDTO>> getTourById(@RequestParam Long id) {
        Optional<TourDTO> tour = Optional.ofNullable(service.getTourById(id));
        return ResponseEntity.ok(tour);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourDTO> deleteTourById(@RequestParam Long id) {
        service.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/optimize")
    public ResponseEntity<TourDTO> createOptimizedTour(@RequestBody TourOptimizationDTO request) {
        TourDTO optimizedTour = service.createOptimizedTour(request);
        return ResponseEntity.ok(optimizedTour);
    }

//    @GetMapping("/stats")
//    public ResponseEntity<TourStatisticsDTO> getTourStats(@RequestParam Long vehicleId,
//                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
//        TourStatisticsDTO stats = service.getTourByVehicleIdAndDate(vehicleId, date);
//        return ResponseEntity.ok(stats);
//    }
}
