package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.DeliveryDTO;
import com.taxist.JibMeak.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(source = "tour.id", target = "tourId")
    DeliveryDTO toDTO(Delivery delivery);

    @Mapping(target = "tour", ignore = true)
    Delivery toEntity(DeliveryDTO deliveryDTO);
}
