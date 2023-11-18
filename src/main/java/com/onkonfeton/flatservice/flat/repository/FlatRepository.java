package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.controller.Params;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Walling;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query("SELECT f FROM Flat f WHERE " +
            "(:#{#params.price[0]} IS NULL OR f.price > :#{#params.price[0]}) AND " +
            "(:#{#params.price[1]} IS NULL OR f.price < :#{#params.price[1]}) AND " +
            "(:#{#params.numberOfRooms} IS NULL OR f.numberOfRooms IN :#{#params.numberOfRooms}) AND"+
            "(:#{#params.area[0]} IS NULL OR f.area.total > :#{#params.area[0]}) AND " +
            "(:#{#params.area[1]} IS NULL OR f.area.total < :#{#params.area[1]}) AND " +
            "(:#{#params.resale} IS NULL OR f.resale = :#{#params.resale}) AND " +
            "(:#{#params.year[0]} IS NULL OR f.year > :#{#params.year[0]}) AND " +
            "(:#{#params.year[1]} IS NULL OR f.year < :#{#params.year[1]}) AND " +
            "(:#{#params.walling} IS NULL OR f.walling IN :walling) AND " +
            "(:#{#params.city} IS NULL OR f.address LIKE %:#{#params.city}%)")
            List<Flat> findByParamsAndPagesAndSort(@Param("params")Params params, @Param("walling") Walling[] walling, Pageable pageable);

}

