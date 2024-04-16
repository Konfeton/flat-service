package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.flat.dto.FlatLightDTO;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.flat.model.Flat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flats")
@RequiredArgsConstructor
public class FlatController {

    private final FlatService flatService;

    @GetMapping
    public List<FlatLightDTO> getAll(){
        return flatService.getAll();
    }

    @GetMapping("/{id}")
    public Flat getOne(@PathVariable long id){
        return flatService.getOne(id);
    }

}
