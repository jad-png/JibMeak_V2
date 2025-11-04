package com.taxist.JibMeakV2.service.interfaces;

import com.taxist.JibMeakV2.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(Long id);

    void deleteCustomer(Long id);
}
