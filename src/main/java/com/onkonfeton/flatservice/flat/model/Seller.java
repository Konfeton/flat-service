package com.onkonfeton.flatservice.flat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Sellers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Column(name = "phone", unique = true)
    private String phone;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flat> flats;

    public Seller(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
