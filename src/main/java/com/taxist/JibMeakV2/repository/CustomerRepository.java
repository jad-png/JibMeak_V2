package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
