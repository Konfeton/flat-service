package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Area {
    @Column(name = "total_area")
    private int total;
    @Column(name = "living_area")
    private int living;
    @Column(name = "kitchen_area")
    private int kitchen;
}


