package com.onkonfeton.flatservice.flat.controller.dto;

import lombok.Data;

@Data
public class ImportanceRequest {
    private int price;
    private int numberOfRooms;
    private int year;
    private int floor;
    private int numberOfFloors;
    private int totalArea;
    private int livingArea;
    private int kitchenArea;
    private int walling;
    private int timeToMetro;
    private int timeToMall;
    private int timeToClinic;
    private int timeToKindergarten;
    private int timeToSchool;
    private int district;
    private int address;
}
