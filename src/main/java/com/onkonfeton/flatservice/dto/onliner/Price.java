package com.onkonfeton.flatservice.dto.onliner;

import com.google.gson.annotations.SerializedName;

public class  Price {
    @SerializedName("amount")
    private String amount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("amount")
    public String getAmount() {
        return amount;
    }
    @SerializedName("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }
    @SerializedName("currency")
    public String getCurrency() {
        return currency;
    }
    @SerializedName("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
