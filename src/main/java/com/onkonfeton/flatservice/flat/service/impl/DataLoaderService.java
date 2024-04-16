package com.onkonfeton.flatservice.flat.service.impl;

import com.onkonfeton.flatservice.flat.converter.FlatConverter;
import com.onkonfeton.flatservice.flat.dto.onliner.ApartmentDTO;
import com.onkonfeton.flatservice.flat.dto.onliner.OnlinerData;
import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.flat.model.Seller;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.flat.service.SellerService;
import com.onkonfeton.flatservice.flat.util.AdditionalInfo;
import com.onkonfeton.flatservice.flat.util.FlatParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataLoaderService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final FlatConverter converter;
    private final FlatParser flatParser;
    private final SellerService sellerService;
    private final FlatService flatService;

    private static final String API_URL = "https://r.onliner.by/sdapi/pk.api/search/apartments?order=created_at%3Adesc";

//    @EventListener(ApplicationReadyEvent.class)
    public void load() throws URISyntaxException {
        OnlinerData onlinerData = restTemplate.getForEntity(new URI(API_URL), OnlinerData.class).getBody();
        List<ApartmentDTO> apartments = onlinerData.getApartments();
        int counter = 0;
        for (ApartmentDTO apartment : apartments) {
            Flat flat = converter.fromDtoToModel(apartment);
            try {
                AdditionalInfo additionalInfo = flatParser.parse(flat.getUrl());
                Optional<Seller> sellerFromDb = sellerService.findByPhone(additionalInfo.getSellerPhone());
                Seller seller;
                if (sellerFromDb.isPresent()){
                    seller = sellerFromDb.get();
                }else {
                    seller = new Seller(additionalInfo.getSellerName(), additionalInfo.getSellerPhone());
                    sellerService.save(seller);
                }
                flat.setSeller(seller);
                flat.setYear(additionalInfo.getYear());
                flat.setDescription(additionalInfo.getDescription());
                flat.setWalling(additionalInfo.getWalling());

                flatService.save(flat);
                counter ++;
//                if (counter == 10){
//                    break;
//                }
            } catch (IOException | IndexOutOfBoundsException e) {
                log.error("Cannot parse flat by url: " + flat.getUrl());
            }
        }
    }
}
