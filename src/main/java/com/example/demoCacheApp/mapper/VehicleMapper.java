package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.VehicleDTO;
import com.taxist.JibMeak.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDTO(Vehicle vehicle);

    Vehicle toEntity(VehicleDTO dto);
}