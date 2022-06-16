package com.dinnerdash.backend.models;

public class Offering {
    public int id;// dummy
    int offeringId;
    int restaurantId;
    String offeringName;
    String offeringDescription;
    int price;
    String offeringPhotoUrl;

    Offering() {
    }

    public Offering(int restaurantID, String offeringName, String offeringDescription, int price, String url) {
        this.restaurantId = restaurantID;
        this.offeringName = offeringName;
        this.offeringDescription = offeringDescription;
        this.price = price;
        this.offeringPhotoUrl = url;
    }

    public int getOfferingId() {
        return this.offeringId;
    }

    public void setOfferingId(int offeringID) {
        this.offeringId = offeringID;
        this.id = offeringID;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantID) {
        this.restaurantId = restaurantID;
    }

    public String getOfferingName() {
        return this.offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public String getOfferingDescription() {
        return this.offeringDescription;
    }

    public void setOfferingDescription(String offeringDescription) {
        this.offeringDescription = offeringDescription;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOfferingPhotoUrl() {
        return this.offeringPhotoUrl;
    }

    public void setOfferingPhotoUrl(String offeringPhotoUrl) {
        this.offeringPhotoUrl = offeringPhotoUrl;
    }

}
