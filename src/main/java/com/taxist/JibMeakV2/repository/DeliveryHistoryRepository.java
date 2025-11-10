package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
    @Query("SELECT dh FROM DeliveryHistory dh JOIN FETCH dh.tour WHERE dh.tour.id = :tourId")
    List<DeliveryHistory> findByTourId(@Param("tourId") Long tourId);

    // better approach for getting history where costumer is included, to n+1
    @Query("Select dh from DeliveryHistory dh " +
            "JOIN FETCH dh.customer c " +
            "JOIN FETCH dh.tour t " +
            "WHERE c.id IN :costumerIds")
    List<DeliveryHistory> findByCostumerIdIn(@Param("costumerIds") List<Long> costumerIds);
}
