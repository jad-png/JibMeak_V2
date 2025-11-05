package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.model.enums.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByDate(LocalDate date);

    List<Tour> findByStatus(TourStatus status);

//    List<Tour> findByTourVehicleIdAndDate(Long vehicleId, LocalDate date);
//
//    @Query("SELECT t FROM Tour t WHERE t.vehicle.id = :vehicleId AND t.date = :date")
//    List<Tour> findTourByVehicleIdAndDate(@Param("vehicleId")Long vehicleId, @Param("date")LocalDate date);
}
