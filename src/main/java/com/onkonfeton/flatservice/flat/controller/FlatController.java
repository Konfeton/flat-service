package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.flat.controller.dto.FlatsPageDto;
import com.onkonfeton.flatservice.flat.controller.dto.ImportanceRequest;
import com.onkonfeton.flatservice.flat.controller.dto.RequestParamsDTO;
import com.onkonfeton.flatservice.flat.converter.FlatConverter;
import com.onkonfeton.flatservice.flat.dto.FlatDTO;
import com.onkonfeton.flatservice.flat.dto.FlatWithoutParams;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.lastviewed.service.LastViewedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/flats")
@RequiredArgsConstructor
@CrossOrigin
public class FlatController {

    private final FlatService flatService;
    private final FlatConverter flatConverter;
    private final LastViewedService lastViewedService;

    @GetMapping
    public ResponseEntity<FlatsPageDto> getByCriteria(RequestParamsDTO params){
        Page<Flat> flatsByCriteria = flatService.getByCriteria(params);

        List<FlatWithoutParams> collect = flatsByCriteria.getContent().stream()
                .map(flatConverter::toDtoWithoutParams)
                .collect(Collectors.toList());

        FlatsPageDto response = new FlatsPageDto();
        response.setFlats(collect);
        response.setTotalPages(flatsByCriteria.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlatDTO> getOne(Principal principal, @PathVariable long id){
        Flat flat = flatService.getOne(id);
        flat.setNumberOfViews(flat.getNumberOfViews() + 1);
        flatService.save(flat);
        if (principal != null) {
            lastViewedService.save(principal, flat);
        }
        FlatDTO dtoWithParams = flatConverter.toDtoWithParams(flat);
        return new ResponseEntity<>(dtoWithParams, HttpStatus.OK);
    }

    @GetMapping("/evaluated")
    public ResponseEntity<List<FlatWithoutParams>> getEvaluatedFlats(Principal principal){
        List<Flat> evaluatedFlats = flatService.getEvaluatedFlats(principal);
        List<FlatWithoutParams> dto = evaluatedFlats.stream()
                .map(flatConverter::toDtoWithoutParams)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/unique")
    public ResponseEntity<List<FlatWithoutParams>> getUniqueFlats(ImportanceRequest request){
        List<Flat> flats = flatService.getUniqueFlats(request);
        List<FlatWithoutParams> dto = flats.stream()
                .map(flatConverter::toDtoWithoutParams)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
