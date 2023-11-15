package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.controller.Params;
import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Walling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlatService {
    @Autowired
    private FlatRepository flatRepository;

    private final int DEFAULT_PAGE_SIZE = 10;

    public Flat findById(Long id) {
        return flatRepository.findById(id).orElseThrow();
    }

    public void save(Flat flat){
        flatRepository.save(flat);
    }

    public List<Flat> findByParamsAndPagesAndSort(Params params, int page, String sort) {
        Walling[] wallings = null;
        if (params.getWalling() != null) {
            try {
                wallings = new Walling[params.getWalling().length];
                Arrays.setAll(wallings, i -> Walling.valueOf(params.getWalling()[i]));
            } catch (IllegalArgumentException e) {
            }
        }
        Pageable pageable;
        if (sort != null) {
            Sort sortBy = null;
            try {
                String[] split = sort.split(":");
                sortBy = Sort.by(Sort.Direction.valueOf(split[1]), split[0]);
            } catch (IllegalArgumentException e) {
                sortBy = Sort.unsorted();
            }
            pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, sortBy);
        }else {
            pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        }


        return flatRepository.findByParamsAndPagesAndSort(params, wallings, pageable);
    }

}
