package com.taxist.JibMeakV2.mapper;

import com.taxist.JibMeakV2.dto.DeliveryHistoryDTO;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryHistoryMapper {

    @Mapping(source = "tour.id", target = "tourId")
    @Mapping(source = "customer.id", target = "customerId")
    DeliveryHistoryDTO toDto(DeliveryHistory dh);
}
