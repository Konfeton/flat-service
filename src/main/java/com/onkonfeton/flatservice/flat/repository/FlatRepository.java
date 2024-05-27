package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

    @Query(value = "SELECT f.* FROM flats f JOIN marks m ON f.id = m.flat_id WHERE m.user_id = :userId", nativeQuery = true)
    List<Flat> findFlatsByUserId(Long userId);

    @Query(value = "SELECT m.flat_id, AVG(m.price) as avgPrice, AVG(m.number_of_rooms) as avgNumberOfRooms, " +
            "AVG(m.year) as avgYear, AVG(m.floor) as avgFloor, " +
            "AVG(m.number_of_floors) as avgNumberOfFloors, AVG(m.total_area) as avgTotalArea, " +
            "AVG(m.living_area) as avgLivingArea, AVG(m.kitchen_area) as avgKitchenArea, " +
            "AVG(m.walling) as avgWalling, " +
            "AVG(m.time_to_metro) as avgTimeToMetro, AVG(m.time_to_mall) as avgTimeToMall, " +
            "AVG(m.time_to_clinic) as avgTimeToClinic, AVG(m.time_to_kindergaten) as avgTimeToKindergarten, " +
            "AVG(m.time_to_school) as avgTimeToSchool, AVG(m.district) as avgDistrict, " +
            "AVG(m.address) as avgAddress " +
            "FROM marks m " +
            "GROUP BY m.flat_id", nativeQuery = true)
    List<Object[]> findAverageMarksForEachFlat();
}

