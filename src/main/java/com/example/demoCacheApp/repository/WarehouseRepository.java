package com.taxist.JibMeak.repository;

import com.taxist.JibMeak.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // no complex queries just basic crud queries
}
