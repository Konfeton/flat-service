package com.onkonfeton.flatservice.user.repository;

import com.onkonfeton.flatservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
