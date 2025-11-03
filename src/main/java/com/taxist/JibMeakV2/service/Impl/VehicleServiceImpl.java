package com.taxist.JibMeakV2.service.Impl;


import com.taxist.JibMeakV2.dto.VehicleDTO;
import com.taxist.JibMeakV2.mapper.VehicleMapper;
import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.repository.VehicleRepository;
import com.taxist.JibMeakV2.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository repo;
    private final VehicleMapper mapper;

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
