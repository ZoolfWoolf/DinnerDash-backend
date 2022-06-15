package com.dinnerdash.backend.payload.request;

import jakarta.validation.constraints.*;

public class RestaurantSignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String name;
    @NotBlank
    private String theme;
    @NotBlank
    private String url;

    public RestaurantSignupRequest() {
    }

    public RestaurantSignupRequest(String username, String email, String password, String name, String theme, String url) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.theme = theme;
        this.url = url;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
