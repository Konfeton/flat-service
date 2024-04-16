package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    @Column(name = "address")
    private String address;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
}
