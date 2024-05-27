package com.onkonfeton.flatservice.marks.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarksResponse {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("flat_id")
    private Long flatId;

    private Integer price;
    private Integer numberOfRooms;
    private Integer year;
    private Integer floor;
    private Integer numberOfFloors;
    private Integer totalArea;
    private Integer livingArea;
    private Integer kitchenArea;
    private Integer resale;
    private Integer walling;
    private Integer timeToMetro;
    private Integer timeToMall;
    private Integer balcony;
    private Integer district;
    private Integer address;

}
