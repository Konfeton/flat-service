package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.flat.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/statistics")
@CrossOrigin(origins = "http://localhost:3000")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;
    @GetMapping("/price")
    public Map<LocalDate, Double> getAvgPriceByDays(){
        return statisticsService.getAvgPriceByDays();
    }

    @GetMapping("/price/m2")
    public Map<LocalDate, Double> getAvgPricePerSquareMeterByDays(){
        return statisticsService.getAvgPricePerSquareMeterByDays();
    }

    @GetMapping("/price/median")
    public Double getMedianPrice(){
        return statisticsService.getMedianPrice();
    }

    @GetMapping("/price/max")
    public int getMaxPrice(){
        return statisticsService.getMaxPrice();
    }

    @GetMapping("/price/min")
    public int getMinPrice(){
        return statisticsService.getMinPrice();
    }

    @GetMapping("/price/mode")
    public int getMode(){
        return statisticsService.getMode();
    }

//    @GetMapping("/flats/{id}/statistics")
//    public Flat get(@PathVariable Long id){
//
//    }

}
