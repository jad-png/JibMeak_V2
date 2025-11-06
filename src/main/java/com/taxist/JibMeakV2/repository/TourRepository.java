package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.model.enums.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByDate(LocalDate date);

    List<Tour> findByStatus(TourStatus status);

    @Query("SELECT t FROM Tour t JOIN FETCH t.deliveries WHERE t.id = :id")
    Optional<Tour> findByIdWithDeliveries(@Param("id") Long id);

//    List<Tour> findByTourVehicleIdAndDate(Long vehicleId, LocalDate date);
//
//    @Query("SELECT t FROM Tour t WHERE t.vehicle.id = :vehicleId AND t.date = :date")
//    List<Tour> findTourByVehicleIdAndDate(@Param("vehicleId")Long vehicleId, @Param("date")LocalDate date);
}
