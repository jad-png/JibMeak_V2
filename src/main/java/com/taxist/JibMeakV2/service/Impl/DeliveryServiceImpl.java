package com.taxist.JibMeakV2.service.Impl;


import com.taxist.JibMeakV2.dto.DeliveryDTO;
import com.taxist.JibMeakV2.mapper.DeliveryMapper;
import com.taxist.JibMeakV2.model.Customer;
import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.repository.CustomerRepository;
import com.taxist.JibMeakV2.repository.DeliveryRepository;
import com.taxist.JibMeakV2.service.interfaces.DeliveryService;
import com.taxist.JibMeakV2.utils.DeliveryValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepo;
    private final DeliveryMapper mapper;
    private final CustomerRepository costumerRepo;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepo,
                               DeliveryMapper mapper,
                               CustomerRepository costumerRepo) {
        this.deliveryRepo = deliveryRepo;
        this.mapper = mapper;
        this.costumerRepo = costumerRepo;
    }

    public DeliveryDTO createDelivery(DeliveryDTO dto) {
        Delivery delivery = mapper.toEntity(dto);
        Customer customer = costumerRepo.findById(dto.getCostumerId()).orElse(null);

        delivery.setCustomer(customer);

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