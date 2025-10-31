package com.taxist.JibMeak.service.Impl;

import com.taxist.JibMeak.dto.VehicleDTO;
import com.taxist.JibMeak.mapper.VehicleMapper;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.repository.VehicleRepository;
import com.taxist.JibMeak.service.interfaces.VehicleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository repo;
    private VehicleMapper mapper;

    public VehicleServiceImpl(VehicleRepository repo, VehicleMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO dto) {
        Vehicle vehicle = mapper.toEntity(dto);

        Vehicle savedTour = repo.save(vehicle);
        return mapper.toDTO(savedTour);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return repo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<VehicleDTO> getVehicleById(Long id) {
        Optional<Vehicle> vehicle = repo.findById(id);
        return vehicle.map(mapper::toDTO);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO dto) {
        Long id = dto.getId();
        Vehicle vehicle = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setType(dto.getType());
        vehicle.setMaxVolumeM3(dto.getMaxVolumeM3());
        vehicle.setMaxWeightKg(dto.getMaxWeightKg());
        vehicle.setMaxDeliveries(dto.getMaxDeliveries());

        Vehicle updated = repo.save(vehicle);
        return mapper.toDTO(updated);
    }

    @Override
    public void deleteVehicle(Long id) {
        repo.deleteById(id);
    }
}
