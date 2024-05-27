package com.onkonfeton.flatservice.flat.dto;

import lombok.Data;

@Data
public class FlatWithoutParams {
    private Long id;
    private double price;
    private int numberOfRooms;
    private int year;
    private int floor;
    private int numberOfFloors;
    private AreaDTO area;
    private String photo;
    private int numberOfViews;

}