package com.taxist.JibMeakV2.service.Impl;

import com.taxist.JibMeakV2.dto.DeliveryHistoryDTO;
import com.taxist.JibMeakV2.mapper.DeliveryHistoryMapper;
import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.DeliveryHistory;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.repository.DeliveryHistoryRepository;
import com.taxist.JibMeakV2.service.interfaces.DeliveryHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryHistoryServiceImpl implements DeliveryHistoryService {
    private final DeliveryHistoryRepository repo;
    private final DeliveryHistoryMapper mapper;

    public DeliveryHistoryServiceImpl(DeliveryHistoryRepository repo, DeliveryHistoryMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
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

    public Page<DeliveryHistoryDTO> searchHistory(
            Long customerId, Long minDelay, LocalDate afterDate, Pageable pageable) {
        Page<DeliveryHistory> historyPage = repo.findAdvancedSearch(customerId, minDelay, afterDate, pageable);

        return historyPage.map(mapper::toDto);
    }

    @Override
    public List<DeliveryHistoryDTO> getHistoryByTourId(Long tourId) {
        return repo.findByTourId(tourId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DeliveryHistoryDTO> getAllHistory() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
