package com.taxist.JibMeakV2.service.Impl;


import com.taxist.JibMeakV2.dto.WarehouseDTO;
import com.taxist.JibMeakV2.mapper.WarehouseMapper;
import com.taxist.JibMeakV2.model.Warehouse;
import com.taxist.JibMeakV2.repository.WarehouseRepository;
import com.taxist.JibMeakV2.service.interfaces.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseImpl implements WarehouseService {
    private final WarehouseRepository repo;
    private final WarehouseMapper mapper;

    public WarehouseImpl(WarehouseRepository repo, WarehouseMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public WarehouseDTO create(WarehouseDTO dto) {
        Warehouse warehouse = mapper.toEntity(dto);

        Warehouse saved = repo.save(warehouse);
        return mapper.toDTO(saved);
    }

    @Override
    public List<WarehouseDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Optional<WarehouseDTO> findById(Long id) {
        Optional<Warehouse> warehouse = repo.findById(id);
        return warehouse.map(mapper::toDTO);
    }

    public WarehouseDTO update(WarehouseDTO dto) {
        Long id = dto.getId();
        Warehouse whs = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        whs.setAddress(dto.getAddress());
        whs.setLatitude(dto.getLatitude());
        whs.setLongitude(dto.getLongitude());
        whs.setOpenHour(dto.getOpenHour());
        whs.setCloseHour(dto.getCloseHour());

        Warehouse saved = repo.save(whs);
        return mapper.toDTO(saved);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
