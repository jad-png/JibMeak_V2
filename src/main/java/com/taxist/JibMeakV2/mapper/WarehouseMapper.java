package com.taxist.JibMeakV2.mapper;

import com.taxist.JibMeakV2.dto.WarehouseDTO;
import com.taxist.JibMeakV2.model.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseDTO toDTO(Warehouse warehouse);

    Warehouse toEntity(WarehouseDTO warehouseDTO);
}