package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.controller.dto.ImportanceRequest;
import com.onkonfeton.flatservice.flat.controller.dto.RequestParamsDTO;
import com.onkonfeton.flatservice.flat.model.Flat;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface FlatService {
    List<Flat> getAll();

    Flat getOne(long id);

    Page<Flat> getByCriteria(RequestParamsDTO params);

    void save(Flat flat);
    List<Flat> getEvaluatedFlats(Principal principal);

    List<Flat> getUniqueFlats(ImportanceRequest request);
}
