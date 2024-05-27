package com.onkonfeton.flatservice.flat.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestParamsDTO {
   private Integer minPrice;
   private Integer maxPrice;
   private List<Integer> numberOfRooms;
   private Integer minSquare;
   private Integer maxSquare;
   private List<Boolean> resale;
   private List<String> walling;
   private String address;
   private String sortBy;
   private Integer page = 0;

}
