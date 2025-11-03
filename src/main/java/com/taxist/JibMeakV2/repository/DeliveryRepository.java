package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // CRUD provided by JpaRepository
    List<Delivery> findByStatus(DeliveryStatus status);
}
