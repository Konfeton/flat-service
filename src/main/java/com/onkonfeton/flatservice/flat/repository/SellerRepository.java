package com.onkonfeton.flatservice.flat.repository;

import com.onkonfeton.flatservice.flat.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByPhone(String phone);
}
