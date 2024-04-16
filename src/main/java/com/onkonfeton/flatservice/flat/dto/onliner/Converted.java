package com.onkonfeton.flatservice.flat.dto.onliner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "BYN",
        "USD"
})
public class Converted {

    @JsonProperty("BYN")
    private Byn byn;
    @JsonProperty("USD")
    private Usd usd;

    @JsonProperty("BYN")
    public Byn getByn() {
        return byn;
    }

    @JsonProperty("BYN")
    public void setByn(Byn byn) {
        this.byn = byn;
    }

    @JsonProperty("USD")
    public Usd getUsd() {
        return usd;
    }

    @JsonProperty("USD")
    public void setUsd(Usd usd) {
        this.usd = usd;
    }

}
