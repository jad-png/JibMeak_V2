package com.taxist.JibMeak.service.Impl;

import com.taxist.JibMeak.dto.DeliveryDTO;
import com.taxist.JibMeak.mapper.DeliveryMapper;
import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.repository.DeliveryRepository;
import com.taxist.JibMeak.service.interfaces.DeliveryService;
import com.taxist.JibMeak.utils.DeliveryValidationUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepo;
    private final DeliveryMapper mapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepo, DeliveryMapper mapper) {
        this.deliveryRepo = deliveryRepo;
        this.mapper = mapper;
    }

    public DeliveryDTO createDelivery(DeliveryDTO dto) {
        Delivery delivery = mapper.toEntity(dto);
        DeliveryValidationUtils.validateDelivery(delivery);
        Delivery savedDelivery = deliveryRepo.save(delivery);
        return mapper.toDTO(savedDelivery);
    }

    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DeliveryDTO> getDelivery(Long id) {
        Optional<Delivery> delivery = deliveryRepo.findById(id);
        return delivery.map(mapper::toDTO);
    }

    public void deleteDelivery(Long id) {
        deliveryRepo.deleteById(id);
    }
}