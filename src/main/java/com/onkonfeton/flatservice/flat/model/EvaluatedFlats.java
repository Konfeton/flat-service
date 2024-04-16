package com.onkonfeton.flatservice.flat.model;

import com.onkonfeton.flatservice.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Entity(name = "Evaluated_flats")
@Getter
@Setter
@NoArgsConstructor
public class EvaluatedFlats {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private User expert;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @Transient
    @Formula(value = "select MAX(flat_id) from Evaluated_flats")
    int max;


}
