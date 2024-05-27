package com.onkonfeton.flatservice.likedflats.model;

import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikedFlats {
    @EmbeddedId
    LikedFlatsKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @MapsId("flatId")
    @JoinColumn(name = "flat_id")
    Flat flat;

    LocalDateTime timestamp;

}
