package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByType(VehicleType type);
}
