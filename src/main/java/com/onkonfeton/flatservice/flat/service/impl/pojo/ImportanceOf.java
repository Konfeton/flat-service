package com.onkonfeton.flatservice.flat.service.impl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportanceOf {
    private double price;
    private double numberOfRooms;
    private double year;
    private double floor; //не подвал и не чердак
    private double numberOfFloors; // меньше = лучше, не муравейник
    private double totalArea;
    private double livingArea;
    private double kitchenArea;
    private double walling; //материал стен
    private double timeToMetro;
    private double timeToMall;
    private double timeToClinic;
    private double timeToKindergarten;
    private double timeToSchool;
    private double district;
    private double address;
}
