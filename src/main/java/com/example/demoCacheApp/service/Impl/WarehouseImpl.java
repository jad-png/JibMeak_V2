package com.taxist.JibMeak.service.Impl;

import com.taxist.JibMeak.dto.VehicleDTO;
import com.taxist.JibMeak.dto.WarehouseDTO;
import com.taxist.JibMeak.mapper.WarehouseMapper;
import com.taxist.JibMeak.model.Warehouse;
import com.taxist.JibMeak.repository.WarehouseRepository;
import com.taxist.JibMeak.service.interfaces.WarehouseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WarehouseImpl implements WarehouseService {
    private WarehouseRepository repo;
    private WarehouseMapper mapper;

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
