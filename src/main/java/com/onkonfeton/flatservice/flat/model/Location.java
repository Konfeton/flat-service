package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Column(name = "address")
    private String address;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
}
