package com.onkonfeton.flatservice.dto;

import com.onkonfeton.flatservice.model.enums.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
    private int amount;
    private Currency currency;
    private double convertedPrice;
    private Currency convertedCurrency;
}
