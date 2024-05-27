package com.onkonfeton.flatservice.marks.model;

import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "marks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marks {
    @EmbeddedId
    private UserFlatKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("flatId")
    @JoinColumn(name = "flat_id")
    private Flat flat;
    @Column(name = "price")
    private int price;
    @Column(name = "number_of_rooms")
    private int numberOfRooms;
    @Column(name = "year")
    private int year;
    @Column(name = "floor")
    private int floor;
    @Column(name = "number_of_floors")
    private int numberOfFloors;
    @Column(name = "total_area")
    private int totalArea;
    @Column(name = "living_area")
    private int livingArea;
    @Column(name = "kitchen_area")
    private int kitchenArea;

    @Column(name = "walling")
    private int walling;

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
    private int district;

    @Column(name = "address")
    private int address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
