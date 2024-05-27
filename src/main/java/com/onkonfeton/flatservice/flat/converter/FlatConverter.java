package com.onkonfeton.flatservice.flat.converter;

import com.onkonfeton.flatservice.flat.dto.FlatDTO;
import com.onkonfeton.flatservice.flat.dto.FlatWithoutParams;
import com.onkonfeton.flatservice.flat.model.Flat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlatConverter {
    private final ModelMapper mapper;
    public FlatWithoutParams toDtoWithoutParams(Flat flat){
        return mapper.map(flat, FlatWithoutParams.class);
    }

    public FlatDTO toDtoWithParams(Flat flat){
        return mapper.map(flat, FlatDTO.class);
    }
}
