package com.dinnerdash.backend.models;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String theme;
    private String url;
    
    public Restaurant(){
    }

    public Restaurant(int id, String name, String theme, String url){
        this.restaurantId = id;
        this.name = name;
        this.theme = theme;
        this.url = url;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
