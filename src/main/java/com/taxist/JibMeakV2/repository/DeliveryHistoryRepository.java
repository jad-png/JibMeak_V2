package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
    @Query("SELECT dh FROM DeliveryHistory dh JOIN FETCH dh.tour WHERE dh.tour.id = :tourId")
    List<DeliveryHistory> findByTourId(@Param("tourId") Long tourId);
}
