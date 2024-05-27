package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Area {
    @Column(name = "total_area")
    private int total;
    @Column(name = "living_area")
    private int living;
    @Column(name = "kitchen_area")
    private int kitchen;
}


