package com.onkonfeton.flatservice.dto.onliner;


import com.google.gson.annotations.SerializedName;

public class AreaDTO {
    @SerializedName("total")
    private double total;
    @SerializedName("living")
    private double living;
    @SerializedName("kitchen")
    private double kitchen;
    @SerializedName("total")
    public double getTotal() {
        return total;
    }
    @SerializedName("total")
    public void setTotal(double total) {
        this.total = total;
    }
    @SerializedName("living")
    public double getLiving() {
        return living;
    }
    @SerializedName("living")
    public void setLiving(double living) {
        this.living = living;
    }
    @SerializedName("kitchen")
    public double getKitchen() {
        return kitchen;
    }
    @SerializedName("kitchen")
    public void setKitchen(double kitchen) {
        this.kitchen = kitchen;
    }
}
