package com.taxist.JibMeak.controller;

import com.taxist.JibMeak.dto.DeliveryDTO;
import com.taxist.JibMeak.service.interfaces.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryDTO> add(@RequestBody DeliveryDTO dto) {
        DeliveryDTO delivery = service.createDelivery(dto);
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> findAll() {
        List<DeliveryDTO> deliveries = service.getAllDeliveries();
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DeliveryDTO>> findDelivery(@PathVariable Long id) {
        Optional<DeliveryDTO> delivery = service.getDelivery(id);
        return ResponseEntity.ok(delivery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }
}