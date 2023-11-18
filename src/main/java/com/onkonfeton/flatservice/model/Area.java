package com.onkonfeton.flatservice.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Area {
    @Column(name = "total_area")
    private double total;
    @Column(name = "living_area")
    private double living;
    @Column(name = "kitchen_area")
    private double kitchen;
}


