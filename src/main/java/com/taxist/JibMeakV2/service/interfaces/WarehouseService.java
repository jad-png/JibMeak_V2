package com.taxist.JibMeakV2.service.interfaces;


import com.taxist.JibMeakV2.dto.WarehouseDTO;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    public WarehouseDTO create(WarehouseDTO dto);
    public List<WarehouseDTO> findAll();
    public Optional<WarehouseDTO> findById(Long id);
    public WarehouseDTO update(WarehouseDTO dto);
    public void deleteById(Long id);
}
