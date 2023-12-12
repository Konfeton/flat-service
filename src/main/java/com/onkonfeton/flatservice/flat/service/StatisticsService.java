package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final FlatRepository flatRepository;

    public StatisticsService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    public Double getAVGPrice(){
        return flatRepository.avg();
    }

    public Double getMedianPrice(){
        return flatRepository.calculateMedian(getCountOfRows()/2);
    }

    private Integer getCountOfRows(){
        return flatRepository.getCount();
    }

    public Map<LocalDate, Double> getAvgPriceByDays() {
        List<Object[]> objects = flatRepository.calculateAvgPriceByDays();

        Map<LocalDate, Double> resultMap = new HashMap<>();

        for (Object[] row : objects) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Double avgPrice = (Double) row[1];
            resultMap.put(date, avgPrice);
        }

        return resultMap;
    }

    public Map<LocalDate, Double> getAvgPricePerSquareMeterByDays() {
        List<Object[]> objects = flatRepository.calculateAvgPricePerSquareMeterByDays();

        Map<LocalDate, Double> resultMap = new HashMap<>();

        for (Object[] row : objects) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Double avgPrice = (Double) row[1];
            resultMap.put(date, avgPrice);
        }

        return resultMap;
    }

    public int getMaxPrice() {
        return flatRepository.getMaxPrice();
    }

    public int getMinPrice() {
        return flatRepository.getMinPrice();
    }

    public int getMode() {
        return flatRepository.getMode();
    }
}
