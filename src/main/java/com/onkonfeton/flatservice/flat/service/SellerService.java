package com.onkonfeton.flatservice.flat.service;

import com.onkonfeton.flatservice.flat.model.Seller;

import java.util.Optional;

public interface SellerService {
    void save(Seller seller);
    Optional<Seller> findByPhone(String phone);
}
