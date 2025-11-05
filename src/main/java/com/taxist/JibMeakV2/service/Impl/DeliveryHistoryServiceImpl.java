package com.taxist.JibMeakV2.service.Impl;

import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.repository.DeliveryHistoryRepository;
import com.taxist.JibMeakV2.service.interfaces.DeliveryHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {
    private final DeliveryHistoryRepository repo;

    public DeliveryHistoryServiceImpl(DeliveryHistoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void generateHistoryForTour(Tour tour) {
        for (Delivery delivery : tour.getDeliveries()) {
            LocalTime planned = delivery.getPreferredWindowStart();
            LocalTime actual = LocalTime.now();

            Long delay = 0L;
            if (planned != null) {
                delay = ChronoUnit.MINUTES.between(planned, actual);
            }

            DeliveryHistory history = new DeliveryHistory();
            history.setCustomer(delivery.getCustomer());
            history.setTour(tour);
            history.setDeliveryDate(tour.getDate());
            history.setDayOfWeek(tour.getDate().getDayOfWeek().toString());
            history.setPlannedTime(planned);
            history.setActualTime(actual);
            history.setDelayInMinutes(delay);

            repo.save(history);
        }
    }
}
