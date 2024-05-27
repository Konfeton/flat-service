package com.onkonfeton.flatservice.lastviewed.repository;

import com.onkonfeton.flatservice.lastviewed.model.LastViewed;
import com.onkonfeton.flatservice.lastviewed.model.LastViewedKey;
import com.onkonfeton.flatservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LastViewedRepository extends JpaRepository<LastViewed, LastViewedKey> {
    List<LastViewed> findByUserOrderByTimestampDesc(User user);
}
