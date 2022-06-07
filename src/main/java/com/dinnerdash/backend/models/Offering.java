package com.dinnerdash.backend.models;

public class Offering {
    int offeringID;
    int restaurantID;
    String offeringName;
    String offeringDescription;
    int price;
    String url;

    Offering(){
    }

    public Offering(int offeringID2, int userId, String offeringName2, String offeringDescription2, int price2,
            String url2) {
    }


    public int getOfferingID() {
        return this.offeringID;
    }

    public void setOfferingID(int offeringID) {
        this.offeringID = offeringID;
    }

    public int getRestaurantID() {
        return this.restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
