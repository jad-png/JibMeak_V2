package com.taxist.JibMeakV2.service.interfaces;


import com.taxist.JibMeakV2.dto.DeliveryDTO;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    public DeliveryDTO createDelivery(DeliveryDTO dto);
    public List<DeliveryDTO> getAllDeliveries();
    public Optional<DeliveryDTO> getDelivery(Long id);
    public void deleteDelivery(Long id);
}

