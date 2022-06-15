package com.dinnerdash.backend.models;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String colorTheme;
    private String bannerUrl;
    
    public Restaurant(){
    }


    public Restaurant(int restaurantId, String restaurantName, String colorTheme, String bannerUrl) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.colorTheme = colorTheme;
        this.bannerUrl = bannerUrl;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getColorTheme() {
        return this.colorTheme;
    }

    public void setColorTheme(String colorTheme) {
        this.colorTheme = colorTheme;
    }

    public String getBannerUrl() {
        return this.bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    

}
