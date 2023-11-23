package com.onkonfeton.flatservice.converter;

import com.onkonfeton.flatservice.dto.FlatDTO;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Currency;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class FlatDTOConverter {

    final ModelMapper modelMapper;

    @PostConstruct
    public void setupMapping(){
        TypeMap<Flat, FlatDTO> typeMap = modelMapper.getTypeMap(Flat.class, FlatDTO.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(Flat.class, FlatDTO.class);
        }


        typeMap.addMappings(mapper -> mapper.<Integer>map(Flat::getPrice, (dest, v) -> dest.getPrice().setAmount(v)))
                .addMappings(mapper -> mapper.<Currency>map(Flat::getCurrency, (dest, v) -> dest.getPrice().setCurrency(v)));

    }
    public FlatDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlatDTO toDTO(Flat flat){
        return modelMapper.map(flat, FlatDTO.class);
    }

    public Flat toEntity(FlatDTO flatDTO){
        return modelMapper.map(flatDTO, Flat.class);
    }
}
