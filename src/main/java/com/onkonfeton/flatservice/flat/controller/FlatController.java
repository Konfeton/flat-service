package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.dto.FlatDTO;
import com.onkonfeton.flatservice.flat.exception.AppException;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.model.Flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/flats")
public class FlatController {
    @Autowired
    private FlatService flatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Flat flatFromDb = flatService.findById(id);
            return new ResponseEntity<>(flatFromDb, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new AppException(HttpStatus.NOT_FOUND.value(), "no flat with id " + id + " found" ), HttpStatus.NOT_FOUND);
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
