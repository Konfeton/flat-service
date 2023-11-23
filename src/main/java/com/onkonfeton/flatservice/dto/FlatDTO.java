package com.onkonfeton.flatservice.dto;

import com.onkonfeton.flatservice.dto.onliner.Page;
import com.onkonfeton.flatservice.model.Area;
import com.onkonfeton.flatservice.model.User;
import com.onkonfeton.flatservice.model.enums.Currency;
import com.onkonfeton.flatservice.model.enums.Walling;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FlatDTO {
    private Long id;
    private String address;
    private UserDto user;
    private int numberOfRooms;
    private String description;
    private int year;
    private int floor;
    private int numberOfFloors;
    private String photo;
    private Walling walling;
    private Area area;
    private boolean resale;
    private Price price;
    private LocalDateTime createdAt;
    private LocalDateTime lastTimeUp;

    private Page page;
}
