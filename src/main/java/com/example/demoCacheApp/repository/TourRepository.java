package com.taxist.JibMeak.repository;

import com.taxist.JibMeak.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByDate(LocalDate date);

//    List<Tour> findByTourVehicleIdAndDate(Long vehicleId, LocalDate date);
//
//    @Query("SELECT t FROM Tour t WHERE t.vehicle.id = :vehicleId AND t.date = :date")
//    List<Tour> findTourByVehicleIdAndDate(@Param("vehicleId")Long vehicleId, @Param("date")LocalDate date);
}
