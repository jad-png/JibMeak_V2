package com.taxist.JibMeakV2.service.interfaces;

import com.taxist.JibMeakV2.dto.DeliveryHistoryDTO;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import com.taxist.JibMeakV2.model.Tour;

import java.util.List;

public interface DeliveryHistoryService {
    void generateHistoryForTour(Tour tour);

    List<DeliveryHistoryDTO> getHistoryByTourId(Long tourId);
    List<DeliveryHistoryDTO> getAllHistory();
}
