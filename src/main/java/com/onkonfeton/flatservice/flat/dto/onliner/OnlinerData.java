package com.onkonfeton.flatservice.flat.dto.onliner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apartments",
        "total",
        "page"
})
@ToString
public class OnlinerData {

    @JsonProperty("apartments")
    private List<ApartmentDTO> apartments;
    @JsonProperty("total")
    private int total;
    @JsonProperty("page")
    private Page page;

    @JsonProperty("apartments")
    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    @JsonProperty("apartments")
    public void setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("page")
    public Page getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Page page) {
        this.page = page;
    }

}
