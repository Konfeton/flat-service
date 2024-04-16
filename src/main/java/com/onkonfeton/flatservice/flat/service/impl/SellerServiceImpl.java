package com.onkonfeton.flatservice.flat.service.impl;

import com.onkonfeton.flatservice.flat.model.Seller;
import com.onkonfeton.flatservice.flat.repository.SellerRepository;
import com.onkonfeton.flatservice.flat.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    @Override
    public void save(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> findByPhone(String phone) {
        return sellerRepository.findByPhone(phone);
    }
}
