package com.taxist.JibMeakV2.repository;

import com.taxist.JibMeakV2.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // no complex queries just basic crud queries
}
