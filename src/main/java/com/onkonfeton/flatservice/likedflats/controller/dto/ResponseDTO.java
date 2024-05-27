package com.onkonfeton.flatservice.likedflats.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("flat_id")
    private Long flatId;
}