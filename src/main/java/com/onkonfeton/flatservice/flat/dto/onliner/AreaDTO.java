package com.onkonfeton.flatservice.flat.dto.onliner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "living",
        "kitchen"
})
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {

    @JsonProperty("total")
    private int total;
    @JsonProperty("living")
    private int living;
    @JsonProperty("kitchen")
    private int kitchen;

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("living")
    public int getLiving() {
        return living;
    }

    @JsonProperty("living")
    public void setLiving(int living) {
        this.living = living;
    }

    @JsonProperty("kitchen")
    public int getKitchen() {
        return kitchen;
    }

    @JsonProperty("kitchen")
    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
    }

}
