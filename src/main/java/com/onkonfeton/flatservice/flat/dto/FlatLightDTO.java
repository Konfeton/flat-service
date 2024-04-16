package com.onkonfeton.flatservice.flat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onkonfeton.flatservice.flat.dto.onliner.AreaDTO;
import com.onkonfeton.flatservice.flat.dto.onliner.Location;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlatLightDTO {
    @JsonProperty( "id")
    private Long id;
    @JsonProperty( "number_of_rooms")
    private int numberOfRooms;
    @JsonProperty( "floor")
    private int floor;
    @JsonProperty( "number_of_floors")
    private int numberOfFloors;
    @JsonProperty( "photo")
    private String photo;
    @JsonProperty( "area")
    private AreaDTO area;
    @JsonProperty( "location")
    private Location location;
    @JsonProperty( "price")
    private double price;
    @JsonProperty( "url")
    private String url;
    @JsonProperty( "created_at")
    private String createdAt;
    @JsonProperty( "last_time_up")
    private String lastTimeUp;
}
