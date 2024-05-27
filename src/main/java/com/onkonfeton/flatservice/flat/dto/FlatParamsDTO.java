package com.onkonfeton.flatservice.flat.dto;

import lombok.Data;

@Data
public class FlatParamsDTO {
   
    private boolean resale;
    private String walling;
    private int timeToMetro;
    private int timeToMall;
    private boolean hasBalcony;
    private String district;
    private LocationDTO location;
    private String description;
}

