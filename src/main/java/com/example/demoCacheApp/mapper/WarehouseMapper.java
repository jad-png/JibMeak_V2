package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.WarehouseDTO;
import com.taxist.JibMeak.model.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseDTO toDTO(Warehouse warehouse);

    Warehouse toEntity(WarehouseDTO warehouseDTO);
}