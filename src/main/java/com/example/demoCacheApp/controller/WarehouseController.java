package com.taxist.JibMeak.controller;

import com.taxist.JibMeak.dto.WarehouseDTO;
import com.taxist.JibMeak.model.Warehouse;
import com.taxist.JibMeak.service.interfaces.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody  WarehouseDTO dto) {
        WarehouseDTO whs = service.create(dto);
        return new ResponseEntity<>(whs, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> findAllWarehouses() {
        List<WarehouseDTO> whs = service.findAll();
        return ResponseEntity.ok(whs);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Optional<WarehouseDTO>> findById(@PathVariable Long id) {
        Optional<WarehouseDTO> whs = service.findById(id);
        return ResponseEntity.ok(whs);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<WarehouseDTO> updateWarehouse(@PathVariable Long id, @RequestBody  WarehouseDTO dto) {
        dto.setId(id);
        WarehouseDTO whs = service.update(dto);
        return ResponseEntity.ok(whs);
    }

    @DeleteMapping
    public ResponseEntity<WarehouseDTO> deleteWarehouse(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
