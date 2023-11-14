package com.onkonfeton.flatservice.flat.controller;

import com.onkonfeton.flatservice.model.enums.Walling;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class Params {
    private Integer[] price;
    private List<Integer> numberOfRooms;
    private Integer[] area;
    private Boolean resale;
    private Integer[] year;
    private String[]  walling;
    private String city;

}
