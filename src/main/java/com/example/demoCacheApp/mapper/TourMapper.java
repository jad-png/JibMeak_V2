package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.TourDTO;
import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "deliveries", target = "deliveryIds")
    TourDTO toDTO(Tour tour);

    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    Tour toEntity(TourDTO tourDTO);

    // custom mapping for deliveries -> delivery ids
    default List<Long> mapDeliveriesToIds(List<Delivery> deliveries) {
        if (deliveries == null) {
            return null;
        }
        return deliveries.stream()
                .map(Delivery::getId)
                .collect(Collectors.toList());
    }

}