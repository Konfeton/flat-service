package com.onkonfeton.flatservice.model;


import com.onkonfeton.flatservice.model.enums.Currency;
import com.onkonfeton.flatservice.model.enums.Walling;
import jakarta.persistence.*;
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

    private String address;
    private int numberOfRooms;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int year;
    private int floor;
    private int numberOfFloors;
    @Column(length = 350)
    private String photo;
    @Enumerated(EnumType.STRING)
    private Walling walling;
    @Embedded
    private Area area;

    private boolean resale;

    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDateTime createdAt;
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
