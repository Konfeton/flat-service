package com.onkonfeton.flatservice.model;


import com.onkonfeton.flatservice.model.enums.Currency;
import com.onkonfeton.flatservice.model.enums.Walling;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flat {
    @Id
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "number_of_rooms")
    private int numberOfRooms;
    @Column(name = "description" ,columnDefinition = "TEXT")
    private String description;
    @Column(name = "year")
    private int year;
    @Column(name = "floor")
    private int floor;
    @Column(name = "number_of_floors")
    private int numberOfFloors;
    @Column(name = "photo",length = 350)
    private String photo;
    @Enumerated(EnumType.STRING)
    private Walling walling;
    @Embedded
    private Area area;
    @Column(name = "resale")
    private boolean resale;

    @Column(name = "price")
    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "last_time_up")
    private LocalDateTime lastTimeUp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
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
