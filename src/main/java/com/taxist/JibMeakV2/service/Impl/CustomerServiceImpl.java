package com.taxist.JibMeakV2.service.Impl;

import com.taxist.JibMeakV2.dto.CustomerDTO;
import com.taxist.JibMeakV2.mapper.CustomerMapper;
import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.repository.CustomerRepository;
import com.taxist.JibMeakV2.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer costumer = mapper.toEntity(customerDTO);
        Customer savedCustomer = repo.save(costumer);
        return mapper.toDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = repo.findAll();
        return customers.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(Long id) {
        Optional<Customer> customer = repo.findById(id);
        return customer.map(mapper::toDTO);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = repo.findById(id).orElse(null);
        repo.delete(customer);
    }
}
