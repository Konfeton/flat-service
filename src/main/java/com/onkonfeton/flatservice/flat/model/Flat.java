package com.onkonfeton.flatservice.flat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity(name = "flats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "price")
    private double price;
    @Column(name = "number_of_rooms")
    private int numberOfRooms;
    @Column(name = "year")
    private int year;
    @Column(name = "floor")
    private int floor;
    @Column(name = "number_of_floors")
    private int numberOfFloors;
    @Embedded
    private Area area;
    @Column(name = "photo",length = 350)
    private String photo;


    @Column(name = "number_of_views")
    private int numberOfViews;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_time_up")
    private LocalDateTime lastTimeUp;

    @OneToOne(mappedBy = "flat", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FlatParams flatParams;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat flat)) return false;

        return getId().equals(flat.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
