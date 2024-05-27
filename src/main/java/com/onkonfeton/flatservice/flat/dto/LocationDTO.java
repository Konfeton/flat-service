package com.onkonfeton.flatservice.flat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationDTO {
    private String address;
    private String latitude;
    private String longitude;
}
