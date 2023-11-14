package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.controller.Params;
import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Walling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlatService {
    @Autowired
    private FlatRepository flatRepository;

    public Flat findById(Long id) {
        return flatRepository.findById(id).orElseThrow();
    }

    public void save(Flat flat){
        flatRepository.save(flat);
    }

    public List<Flat> findByParams(Params params) {
        Walling[] wallings = null;
        if (params.getWalling() != null) {
            try {
                wallings = new Walling[params.getWalling().length];
                Arrays.setAll(wallings, i -> Walling.valueOf(params.getWalling()[i]));
            } catch (IllegalArgumentException e) {
            }
        }
        return flatRepository.findByParams(params, wallings);
    }

}
