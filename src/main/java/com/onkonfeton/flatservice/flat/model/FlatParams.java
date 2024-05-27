package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "flat_params")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatParams {
    @Id
    @Column(name = "flat_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @Column(name = "resale")
    private boolean resale;

    @Column(name = "walling")
    private String walling;

    @Column(name = "time_to_metro")
    private int timeToMetro;
    @Column(name = "time_to_mall")
    private int timeToMall;

    @Column(name = "time_to_clinic")
    private int timeToClinic;
    @Column(name = "time_to_kindergaten")
    private int timeToKindergarten;
    @Column(name = "time_to_school")
    private int timeToSchool;
    @Column(name = "district")
    private String district;

    @Embedded
    private Location location;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
