package com.onkonfeton.flatservice.likedflats.repository;

import com.onkonfeton.flatservice.likedflats.model.LikedFlats;
import com.onkonfeton.flatservice.likedflats.model.LikedFlatsKey;
import com.onkonfeton.flatservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedFlatsRepository extends JpaRepository<LikedFlats, LikedFlatsKey> {
    List<LikedFlats> findByUserOrderByTimestampDesc(User user);
}
