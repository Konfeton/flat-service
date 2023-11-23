package com.onkonfeton.flatservice.dto.onliner;


import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public class ApartmentDTO {
    @SerializedName("id")
    private Long id;
    @SerializedName("author_id")
    private Long authorId;
    @SerializedName("location")
    private Location location;
    @SerializedName("price")
    private Price price;
    @SerializedName("photo")
    private String photo;
    @SerializedName("resale")
    private boolean resale;
    @SerializedName("number_of_rooms")
    private int numberOfRooms;
    @SerializedName("floor")
    private int floor;
    @SerializedName("number_of_floors")
    private int numberOfFloors;
    @SerializedName("area")
    private AreaDTO area;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
    @SerializedName("last_time_up")
    private LocalDateTime  lastTimeUp;

    @SerializedName("id")
    public Long getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(Long id) {
        this.id = id;
    }

    @SerializedName("author_id")
    public Long getAuthorId() {
        return authorId;
    }

    @SerializedName("author_id")
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @SerializedName("location")
    public Location getLocation() {
        return location;
    }

    @SerializedName("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @SerializedName("price")
    public Price getPrice() {
        return price;
    }

    @SerializedName("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @SerializedName("photo")
    public String getPhoto() {
        return photo;
    }

    @SerializedName("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @SerializedName("resale")
    public boolean getResale() {
        return resale;
    }

    @SerializedName("resale")
    public void setResale(boolean resale) {
        this.resale = resale;
    }

    @SerializedName("number_of_rooms")
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    @SerializedName("number_of_rooms")
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @SerializedName("floor")
    public int getFloor() {
        return floor;
    }

    @SerializedName("floor")
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @SerializedName("number_of_floors")
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @SerializedName("number_of_floors")
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @SerializedName("area")
    public AreaDTO getArea() {
        return area;
    }

    @SerializedName("area")
    public void setArea(AreaDTO area) {
        this.area = area;
    }

    @SerializedName("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @SerializedName("created_at")
    public void setCreatedAt(LocalDateTime  createdAt) {
        this.createdAt = createdAt;
    }

    @SerializedName("last_time_up")
    public LocalDateTime getLastTimeUp() {
        return lastTimeUp;
    }

    @SerializedName("last_time_up")
    public void setLastTimeUp(LocalDateTime  lastTimeUp) {
        this.lastTimeUp = lastTimeUp;
    }

    @Override
    public String toString() {
        return "ApartmentDTO{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", location=" + location +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", resale=" + resale +
                ", numberOfRooms=" + numberOfRooms +
                ", floor=" + floor +
                ", numberOfFloors=" + numberOfFloors +
                ", area=" + area +
                ", createdAt=" + createdAt +
                ", lastTimeUp=" + lastTimeUp +
                '}';
    }
}
