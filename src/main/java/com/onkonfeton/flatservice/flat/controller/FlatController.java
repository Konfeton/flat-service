package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.dto.FlatDTO;
import com.onkonfeton.flatservice.flat.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/flats")
@CrossOrigin(origins = "http://localhost:3000")
public class FlatController {
    @Autowired
    private FlatService flatService;

    @GetMapping("/{id}")
    public FlatDTO getById(@PathVariable Long id) {
        try {
            FlatDTO flatFromDb = flatService.findById(id);
            return flatFromDb;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @GetMapping("")
    public List<FlatDTO> getByParams(@RequestParam(required = false) Integer minPrice,
                                     @RequestParam(required = false) Integer maxPrice,
                                     @RequestParam(required = false) List<Integer> numberOfRooms,
                                     @RequestParam(required = false) Integer minArea,
                                     @RequestParam(required = false) Integer maxArea,
                                     @RequestParam(required = false) Boolean resale,
                                     @RequestParam(required = false) Integer minYear,
                                     @RequestParam(required = false) Integer maxYear,
                                     @RequestParam(required = false) String[] walling,
                                     @RequestParam(required = false) String city,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false) String order) {

        Params params = new Params();
        params.setPrice(new Integer[]{minPrice, maxPrice});
        params.setNumberOfRooms(numberOfRooms);
        params.setArea(new Integer[]{minArea, maxArea});
        params.setResale(resale);
        params.setYear(new Integer[]{minYear, maxYear});
        params.setWalling(walling);
        params.setCity(city);

        return flatService.findByParamsAndPagesAndSort(params, page, order);
    }



}
