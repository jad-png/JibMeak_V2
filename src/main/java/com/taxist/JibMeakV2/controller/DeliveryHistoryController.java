package com.taxist.JibMeakV2.controller;

import com.taxist.JibMeakV2.dto.DeliveryHistoryDTO;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import com.taxist.JibMeakV2.service.interfaces.DeliveryHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class DeliveryHistoryController {
    private final DeliveryHistoryService service;

    public DeliveryHistoryController(DeliveryHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryHistoryDTO>> getAllHistory() {
        List<DeliveryHistoryDTO> history = service.getAllHistory();
        return ResponseEntity.ok(history);
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<List<DeliveryHistoryDTO>> getHistoryForTour(@PathVariable Long id) {
        List<DeliveryHistoryDTO> history = service.getHistoryByTourId(id);
        return ResponseEntity.ok(history);
    }
}
