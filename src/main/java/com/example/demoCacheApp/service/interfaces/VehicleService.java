package com.taxist.JibMeak.service.interfaces;

import com.taxist.JibMeak.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    public VehicleDTO createVehicle(VehicleDTO dto);
    public List<VehicleDTO> getAllVehicles();
    public Optional<VehicleDTO> getVehicleById(Long id);
    public VehicleDTO updateVehicle(VehicleDTO dto);
    public void deleteVehicle(Long id);
}
