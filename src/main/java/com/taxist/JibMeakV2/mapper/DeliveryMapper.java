package com.taxist.JibMeakV2.mapper;

import com.taxist.JibMeakV2.dto.DeliveryDTO;
import com.taxist.JibMeakV2.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(source = "tour.id", target = "tourId")
    @Mapping(source = "customer.id", target = "costumerId")
    DeliveryDTO toDTO(Delivery delivery);

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "customer", ignore = true )
    Delivery toEntity(DeliveryDTO deliveryDTO);
}
