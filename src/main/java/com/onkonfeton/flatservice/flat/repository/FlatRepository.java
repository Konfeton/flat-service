package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.controller.Params;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Walling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query("SELECT f FROM Flat f WHERE " +
            "(:#{#params.price[0]} IS NULL OR f.price > :#{#params.price[0]}) AND " +
            "(:#{#params.price[1]} IS NULL OR f.price < :#{#params.price[1]}) AND " +
            "(:#{#params.numberOfRooms} IS NULL OR f.numberOfRooms IN :#{#params.numberOfRooms}) AND" +
            "(:#{#params.area[0]} IS NULL OR f.area.total > :#{#params.area[0]}) AND " +
            "(:#{#params.area[1]} IS NULL OR f.area.total < :#{#params.area[1]}) AND " +
            "(:#{#params.resale} IS NULL OR f.resale = :#{#params.resale}) AND " +
            "(:#{#params.year[0]} IS NULL OR f.year > :#{#params.year[0]}) AND " +
            "(:#{#params.year[1]} IS NULL OR f.year < :#{#params.year[1]}) AND " +
            "(:#{#params.walling} IS NULL OR f.walling IN :walling) AND " +
            "(:#{#params.city} IS NULL OR f.address LIKE %:#{#params.city}%)")
    Page<Flat> findByParamsAndPagesAndSort(@Param("params") Params params, @Param("walling") Walling[] walling, Pageable pageable);

    @Query("SELECT AVG(price) FROM Flat")
    Double avg();

    @Query(value = "SELECT MAX(price) as median FROM (SELECT price from Flat  ORDER BY price limit :lim) AS sampled_rows", nativeQuery = true )
    Double calculateMedian(@Param("lim") long lim);
    @Query("SELECT COUNT(*) FROM Flat")
    Integer getCount();

    @Query(value = "select DATE(last_time_up), avg(price) as price from flat group by DATE(last_time_up)", nativeQuery = true)
    List<Object[]> calculateAvgPriceByDays();

    @Query(value = "select DATE(last_time_up), avg(price/total_area) as price from flat group by DATE(last_time_up)", nativeQuery = true)
    List<Object[]> calculateAvgPricePerSquareMeterByDays();

    @Query(value = "select max(price) from flat", nativeQuery = true)
    int getMaxPrice();

    @Query(value = "select min(price) from flat", nativeQuery = true)
    int getMinPrice();

    @Query(value = "select price, count(price) as number from flat group by price order by number desc limit 1", nativeQuery = true)
    int getMode();
}

