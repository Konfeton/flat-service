package com.onkonfeton.flatservice.dto.onliner;

import com.google.gson.annotations.SerializedName;
public class Location {
    @SerializedName("address")
    private String address;
    @SerializedName("address")
    public String getAddress() {
        return address;
    }
    @SerializedName("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                '}';
    }
}
