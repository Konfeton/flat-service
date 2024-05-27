package com.onkonfeton.flatservice.flat.controller.dto;

import lombok.Data;

@Data
public class ImportanceRequest {
    private int price = 0;
    private int numberOfRooms = 0;
    private int year = 0;
    private int floor = 0; //не подвал и не чердак
    private int numberOfFloors = 0; // меньше = лучше, не муравейник
    private int totalArea = 0;
    private int livingArea = 0;
    private int kitchenArea = 0;
    private int walling = 0; //материал стен
    private int timeToMetro = 0;
    private int timeToMall = 0;
    private int timeToClinic = 0;
    private int timeToKindergarten = 0;
    private int timeToSchool = 0;
    private int district = 0;
    private int address = 0;
}
