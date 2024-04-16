package com.onkonfeton.flatservice.flat.dto.onliner;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "author_id",
        "location",
        "price",
        "photo",
        "resale",
        "number_of_rooms",
        "floor",
        "number_of_floors",
        "area",
        "seller",
        "created_at",
        "last_time_up",
        "up_available_in",
        "url",
        "auction_bid"
})
@ToString
public class ApartmentDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("author_id")
    private int authorId;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("resale")
    private boolean resale;
    @JsonProperty("number_of_rooms")
    private int numberOfRooms;
    @JsonProperty("floor")
    private int floor;
    @JsonProperty("number_of_floors")
    private int numberOfFloors;
    @JsonProperty("area")
    private AreaDTO area;
    @JsonProperty("seller")
    private Seller seller;
    @JsonFormat(pattern = "yyyy-MM-dd'T'H:mm:ssXXX")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'H:mm:ssXXX")
    @JsonProperty("last_time_up")
    private LocalDateTime lastTimeUp;
    @JsonProperty("up_available_in")
    private int upAvailableIn;
    @JsonProperty("url")
    private String url;
    @JsonProperty("auction_bid")
    private Object auctionBid;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("author_id")
    public int getAuthorId() {
        return authorId;
    }

    @JsonProperty("author_id")
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("resale")
    public boolean isResale() {
        return resale;
    }

    @JsonProperty("resale")
    public void setResale(boolean resale) {
        this.resale = resale;
    }

    @JsonProperty("number_of_rooms")
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @JsonProperty("number_of_rooms")
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @JsonProperty("floor")
    public int getFloor() {
        return floor;
    }

    @JsonProperty("floor")
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @JsonProperty("number_of_floors")
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @JsonProperty("number_of_floors")
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @JsonProperty("area")
    public AreaDTO getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(AreaDTO area) {
        this.area = area;
    }

    @JsonProperty("seller")
    public Seller getSeller() {
        return seller;
    }

    @JsonProperty("seller")
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @JsonProperty("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("last_time_up")
    public LocalDateTime getLastTimeUp() {
        return lastTimeUp;
    }

    @JsonProperty("last_time_up")
    public void setLastTimeUp(LocalDateTime lastTimeUp) {
        this.lastTimeUp = lastTimeUp;
    }

    @JsonProperty("up_available_in")
    public int getUpAvailableIn() {
        return upAvailableIn;
    }

    @JsonProperty("up_available_in")
    public void setUpAvailableIn(int upAvailableIn) {
        this.upAvailableIn = upAvailableIn;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("auction_bid")
    public Object getAuctionBid() {
        return auctionBid;
    }

    @JsonProperty("auction_bid")
    public void setAuctionBid(Object auctionBid) {
        this.auctionBid = auctionBid;
    }

}
