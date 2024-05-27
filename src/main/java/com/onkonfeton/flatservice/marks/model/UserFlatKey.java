package com.onkonfeton.flatservice.marks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserFlatKey implements Serializable {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "flat_id")
    Long flatId;
}