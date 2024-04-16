package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.model.EvaluatedFlats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluatedFlatsRepository extends JpaRepository<EvaluatedFlats, Long> {
}
