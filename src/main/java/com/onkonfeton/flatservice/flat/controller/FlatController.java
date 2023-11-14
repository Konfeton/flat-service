package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Walling;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flats")
public class FlatController {
    @Autowired
    private FlatService flatService;
    @GetMapping("/{id}")
    public Flat getById(@PathVariable Long id) {
        return flatService.findById(id);
    }

    @GetMapping("")
    public List<Flat> getByParams(@RequestParam(required = false) Integer minPrice,
                                  @RequestParam(required = false) Integer maxPrice,
                                  @RequestParam(required = false) List<Integer> numberOfRooms,
                                  @RequestParam(required = false) Integer minArea,
                                  @RequestParam(required = false) Integer maxArea,
                                  @RequestParam(required = false) Boolean resale,
                                  @RequestParam(required = false) Integer minYear,
                                  @RequestParam(required = false) Integer maxYear,
                                  @RequestParam(required = false) String[] walling,
                                  @RequestParam(required = false) String city) {

        Params params = new Params();
        params.setPrice(new Integer[]{minPrice, maxPrice});
        params.setNumberOfRooms(numberOfRooms);
        params.setArea(new Integer[]{minArea, maxArea});
        params.setResale(resale);
        params.setYear(new Integer[]{minYear, maxYear});
        params.setWalling(walling);
        params.setCity(city);

        return flatService.findByParams(params);
    }

}
