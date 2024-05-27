package com.onkonfeton.flatservice.marks.repository;

import com.onkonfeton.flatservice.marks.model.Marks;
import com.onkonfeton.flatservice.marks.model.UserFlatKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, UserFlatKey> {

}
