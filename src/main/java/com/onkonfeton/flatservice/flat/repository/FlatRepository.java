package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query(value = "SELECT f.id, f.number_of_rooms, f.floor, f.number_of_floors, " +
            "f.photo, f.total_area, f.living_area, f.kitchen_area, f.address, f.latitude, " +
            "f.longitude, f.price, f.url, f.created_at, f.last_time_up\n" +
            "FROM Flats f", nativeQuery = true)
    List<Object[]> getAllCompact();
}

