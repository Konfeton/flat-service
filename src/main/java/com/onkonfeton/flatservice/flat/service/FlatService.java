package com.onkonfeton.flatservice.flat.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.onkonfeton.flatservice.converter.FlatDTOConverter;
import com.onkonfeton.flatservice.dto.FlatDTO;
import com.onkonfeton.flatservice.flat.controller.Params;
import com.onkonfeton.flatservice.flat.repository.FlatRepository;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.enums.Currency;
import com.onkonfeton.flatservice.model.enums.Walling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatService {
    @Autowired
    private FlatRepository flatRepository;
    @Autowired
    FlatDTOConverter flatDTOConverter;
    private final int DEFAULT_PAGE_SIZE = 9;

    public FlatDTO findById(Long id) {
        FlatDTO flatDTO = flatDTOConverter.toDTO(flatRepository.findById(id).orElseThrow());
        double currOfficialRate = getCurrOfficialRate();
        flatDTO.getPrice().setConvertedPrice(flatDTO.getPrice().getAmount() * currOfficialRate);
        flatDTO.getPrice().setConvertedCurrency(Currency.BYN);
        return flatDTO;
    }

    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    public List<FlatDTO> findByParamsAndPagesAndSort(Params params, int page, String sort) {
        Walling[] wallings = null;
        if (params.getWalling() != null) {
            try {
                wallings = new Walling[params.getWalling().length];
                Arrays.setAll(wallings, i -> Walling.valueOf(params.getWalling()[i]));
            } catch (IllegalArgumentException e) {
            }
        }
        Pageable pageable;
        if (sort != null) {
            Sort sortBy = null;
            try {
                String[] split = sort.split(":");
                sortBy = Sort.by(Sort.Direction.valueOf(split[1]), split[0]);
            } catch (IllegalArgumentException e) {
                sortBy = Sort.unsorted();
            }
            pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, sortBy);
        } else {
            pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        }

        Page<Flat> flatPage = flatRepository.findByParamsAndPagesAndSort(params, wallings, pageable);
        double currOfficialRate = getCurrOfficialRate();
        List<FlatDTO> flats = flatPage.map(el -> flatDTOConverter.toDTO(el))
                .map(flatDTO -> {
                    flatDTO.setPage(new com.onkonfeton.flatservice.dto.onliner.Page(pageable.getPageSize(), flatPage.getSize(), pageable.getPageNumber(), flatPage.getTotalPages()));
                    flatDTO.getPrice().setConvertedCurrency(Currency.BYN);
                    flatDTO.getPrice().setConvertedPrice(flatDTO.getPrice().getAmount() * currOfficialRate);
                    return flatDTO;
                })
                .stream().collect(Collectors.toList());

        return flats;
    }

    private double getCurrOfficialRate() {
        HttpRequest httpRequest = null;
        HttpResponse<String> response = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://api.nbrb.by/exrates/rates/431"))
                    .header("Accept", "*/*")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);
        return jsonObject.get("Cur_OfficialRate").getAsDouble();
    }

}
