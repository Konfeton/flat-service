package com.onkonfeton.flatservice.flat.converter;

import com.onkonfeton.flatservice.flat.dto.FlatLightDTO;
import com.onkonfeton.flatservice.flat.dto.onliner.ApartmentDTO;
import com.onkonfeton.flatservice.flat.model.Flat;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlatConverter {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void initMapping(){
        TypeMap<ApartmentDTO, Flat> typeMap = modelMapper.getTypeMap(ApartmentDTO.class, Flat.class);
        if (typeMap == null){
            typeMap = modelMapper.createTypeMap(ApartmentDTO.class, Flat.class);
        }

        typeMap.addMappings(mapper -> mapper.skip(Flat::setSeller));
        typeMap.addMapping(s -> s.getPrice().getAmount(), Flat::setPrice);
    }

    public Flat fromDtoToModel(ApartmentDTO apartmentDTO){
        Flat flat = modelMapper.map(apartmentDTO, Flat.class);
        return flat;
    }

    public FlatLightDTO fromModelToDTO(Flat flat) {
        FlatLightDTO flatDTO = modelMapper.map(flat, FlatLightDTO.class);
        return flatDTO;
    }
}
