package com.onkonfeton.flatservice.flat.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class FlatDTO {
    private Long id;
    private double price;
    private int numberOfRooms;
    private int year;
    private int floor;
    private int numberOfFloors;
    private AreaDTO area;
    private String photo;
    private int numberOfViews;
    private LocalDateTime createdAt;
    private LocalDateTime lastTimeUp;
    private FlatParamsDTO flatParams;
}
