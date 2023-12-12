package com.onkonfeton.flatservice.converter;

import com.onkonfeton.flatservice.dto.onliner.ApartmentDTO;
import com.onkonfeton.flatservice.dto.onliner.Location;
import com.onkonfeton.flatservice.dto.onliner.Price;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Currency;
import jakarta.annotation.PostConstruct;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ApartmentConverter {
    private final ModelMapper modelMapper;

    public ApartmentConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapping(){
        TypeMap<ApartmentDTO, Flat> typeMap = modelMapper.getTypeMap(ApartmentDTO.class, Flat.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(ApartmentDTO.class, Flat.class);
        }

        Converter<Location, String> toAddress = new AbstractConverter<>() {
            @Override
            protected String convert(Location location) {
                return location.getAddress();
            }
        };
        typeMap.addMappings(mapper -> mapper.using(toAddress).map(ApartmentDTO::getLocation, Flat::setAddress));

        Converter<Price, Double> toAmount = new AbstractConverter<Price, Double>() {
            @Override
            protected Double convert(Price price) {
                return Double.parseDouble(price.getAmount());
            }
        };



        typeMap.addMappings(mapper -> mapper.using(toAmount).map(ApartmentDTO::getPrice, Flat::setPrice));

        Converter<Price, Currency> toCurrency = new AbstractConverter<Price, Currency>() {
            @Override
            protected Currency convert(Price price) {
                return Currency.valueOf(price.getCurrency());
            }
        };

        typeMap.addMappings(mapper -> mapper.using(toCurrency).map(ApartmentDTO::getPrice, Flat::setCurrency));

    }

    public Flat fromApartmentToFlat(ApartmentDTO apartmentDTO){
        return modelMapper.map(apartmentDTO, Flat.class);
    }
}
