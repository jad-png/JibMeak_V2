package com.taxist.JibMeakV2.controller;

import com.taxist.JibMeakV2.dto.DeliveryHistoryDTO;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import com.taxist.JibMeakV2.service.interfaces.DeliveryHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
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

    @GetMapping("/search")
    public ResponseEntity<Page<DeliveryHistoryDTO>> searchHistory(
            @RequestParam Long customerId,
            @RequestParam(defaultValue = "0") Long minDelay,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate afterDate,
            Pageable pageable) { // <-- Spring creates this automatically

        Page<DeliveryHistoryDTO> results = service.searchHistory(
                customerId, minDelay, afterDate, pageable);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<List<DeliveryHistoryDTO>> getHistoryForTour(@PathVariable Long id) {
        List<DeliveryHistoryDTO> history = service.getHistoryByTourId(id);
        return ResponseEntity.ok(history);
    }
}
