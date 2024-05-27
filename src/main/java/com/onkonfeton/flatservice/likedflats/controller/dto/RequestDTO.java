package com.onkonfeton.flatservice.likedflats.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestDTO {
    @JsonProperty("flat_id")
    private long flatId;
}
