package com.onkonfeton.flatservice.model;


import com.onkonfeton.flatservice.model.enums.Currency;
import com.onkonfeton.flatservice.model.enums.Walling;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Flat {
    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private int numberOfRooms;
    private String description;
    private int year;
    private int floor;
    private int numberOfFloors;
    @Enumerated(EnumType.STRING)
    private Walling walling;
    @Embedded
    private Area area;

    private boolean resale;

    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDateTime createdAt;
    private LocalDateTime lastTimeUpdated;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
