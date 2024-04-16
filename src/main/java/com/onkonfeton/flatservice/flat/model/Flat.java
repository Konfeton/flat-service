package com.onkonfeton.flatservice.flat.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Entity(name = "Flats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flat {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "number_of_rooms")
    private int numberOfRooms;
    @Column(name = "year")
    private int year;
    @Column(name = "floor")
    private int floor;
    @Column(name = "number_of_floors")
    private int numberOfFloors;
    @Column(name = "photo",length = 350)
    private String photo;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "resale")
    private boolean resale;
    @Column(name = "walling")
    private String walling;
    @Embedded
    private Area area;
    @Embedded
    private Location location;
    @Column(name = "price")
    private double price;
    @Column(name = "url")
    private String url;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_time_up")
    private LocalDateTime lastTimeUp;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @ToString.Exclude
    private Seller seller;

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
