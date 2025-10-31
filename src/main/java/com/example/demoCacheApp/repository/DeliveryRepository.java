package com.taxist.JibMeak.repository;

import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // CRUD provided by JpaRepository
    List<Delivery> findByStatus(DeliveryStatus status);
}
