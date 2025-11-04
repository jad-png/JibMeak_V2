package com.taxist.JibMeakV2.mapper;

import com.taxist.JibMeakV2.dto.CustomerDTO;
import com.taxist.JibMeakV2.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}