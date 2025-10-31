package com.taxist.JibMeak.controller;

import com.taxist.JibMeak.dto.TourDTO;
import com.taxist.JibMeak.dto.TourOptimizationDTO;
import com.taxist.JibMeak.dto.VehicleDTO;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.service.interfaces.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> add(@RequestBody VehicleDTO dto) {
        VehicleDTO vehicle = service.createVehicle(dto);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
        List<VehicleDTO> vehicles = service.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<VehicleDTO>> findVehicles(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = service.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> update(@RequestBody VehicleDTO dto) {
        VehicleDTO vehicle = service.updateVehicle(dto);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
