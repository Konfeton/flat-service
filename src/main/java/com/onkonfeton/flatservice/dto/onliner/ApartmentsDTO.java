package com.onkonfeton.flatservice.dto.onliner;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ApartmentsDTO {
    @SerializedName("apartments")
    private List<ApartmentDTO> apartments;
    @SerializedName("total")
    private int total;
    @SerializedName("page")
    private Page page;

    @SerializedName("apartments")
    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    @SerializedName("apartments")
    public void setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
    }

    @SerializedName("total")
    public int getTotal() {
        return total;
    }

    @SerializedName("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @SerializedName("page")
    public Page getPage() {
        return page;
    }

    @SerializedName("page")
    public void setPage(Page page) {
        this.page = page;
    }
}





