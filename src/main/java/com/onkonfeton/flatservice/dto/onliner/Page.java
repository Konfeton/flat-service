package com.onkonfeton.flatservice.dto.onliner;

import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("limit")
    private int limit;
    @SerializedName("items")
    private int items;
    @SerializedName("current")
    private int current;
    @SerializedName("last")
    private int last;
    public Page(int limit, int items, int current, int last) {
        this.limit = limit;
        this.items = items;
        this.current = current;
        this.last = last;
    }

    @SerializedName("limit")
    public int getLimit() {
        return limit;
    }

    @SerializedName("limit")
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @SerializedName("items")
    public int getItems() {
        return items;
    }

    @SerializedName("items")
    public void setItems(int items) {
        this.items = items;
    }

    @SerializedName("current")
    public int getCurrent() {
        return current;
    }

    @SerializedName("current")
    public void setCurrent(int current) {
        this.current = current;
    }

    @SerializedName("last")
    public int getLast() {
        return last;
    }

    @SerializedName("last")
    public void setLast(int last) {
        this.last = last;
    }
}
