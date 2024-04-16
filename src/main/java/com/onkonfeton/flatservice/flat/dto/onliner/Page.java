package com.onkonfeton.flatservice.flat.dto.onliner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "limit",
        "items",
        "current",
        "last"
})

public class Page {

    @JsonProperty("limit")
    private int limit;
    @JsonProperty("items")
    private int items;
    @JsonProperty("current")
    private int current;
    @JsonProperty("last")
    private int last;

    @JsonProperty("limit")
    public int getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @JsonProperty("items")
    public int getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(int items) {
        this.items = items;
    }

    @JsonProperty("current")
    public int getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(int current) {
        this.current = current;
    }

    @JsonProperty("last")
    public int getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(int last) {
        this.last = last;
    }

}
