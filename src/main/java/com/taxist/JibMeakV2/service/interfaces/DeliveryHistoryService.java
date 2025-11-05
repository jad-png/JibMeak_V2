package com.taxist.JibMeakV2.service.interfaces;

import com.taxist.JibMeakV2.model.Tour;

public interface DeliveryHistoryService {
    void generateHistoryForTour(Tour tour);
}
