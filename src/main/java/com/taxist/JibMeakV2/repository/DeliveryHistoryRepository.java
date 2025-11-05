package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
}
