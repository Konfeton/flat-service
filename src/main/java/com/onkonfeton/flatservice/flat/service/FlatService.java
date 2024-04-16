package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.dto.FlatLightDTO;
import com.onkonfeton.flatservice.flat.model.Flat;

import java.util.List;

public interface FlatService {
    List<FlatLightDTO> getAll();
    Flat getOne(long id);
    void save(Flat flat);
}
