package com.taxist.JibMeakV2.mapper;

import com.taxist.JibMeakV2.dto.VehicleDTO;
import com.taxist.JibMeakV2.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDTO(Vehicle vehicle);

    Vehicle toEntity(VehicleDTO dto);
}