package com.onkonfeton.flatservice.flat.controller.dto;

import com.onkonfeton.flatservice.flat.dto.FlatWithoutParams;
import lombok.Data;

import java.util.List;

@Data
public class FlatsPageDto {
    private List<FlatWithoutParams> flats;
    private int totalPages;
}