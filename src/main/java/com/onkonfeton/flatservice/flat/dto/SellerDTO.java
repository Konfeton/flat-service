package com.onkonfeton.flatservice.flat.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO{
    @JsonProperty( "name")
    private String name;
    @JsonProperty( "phone")
    private String phone;
}