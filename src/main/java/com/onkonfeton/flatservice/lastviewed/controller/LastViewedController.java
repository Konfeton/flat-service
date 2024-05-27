package com.onkonfeton.flatservice.lastviewed.controller;

import com.onkonfeton.flatservice.flat.converter.FlatConverter;
import com.onkonfeton.flatservice.flat.dto.FlatWithoutParams;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.lastviewed.service.LastViewedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flats/lastViewed")
@RequiredArgsConstructor
@CrossOrigin
public class LastViewedController {
    private final LastViewedService lastViewedService;
    private final FlatConverter flatConverter;


    @GetMapping
    public ResponseEntity<List<FlatWithoutParams>> getLastViewed(Principal principal){
        List<Flat> lastViewed = lastViewedService.getLastViewed(principal);
        List<FlatWithoutParams> flats = lastViewed.stream()
                .map(flatConverter::toDtoWithoutParams)
                .collect(Collectors.toList());
        return new ResponseEntity<>(flats, HttpStatus.OK);
    }

}
