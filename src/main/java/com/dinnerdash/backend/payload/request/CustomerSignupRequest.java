package com.dinnerdash.backend.payload.request;

import jakarta.validation.constraints.*;

public class CustomerSignupRequest {
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

    @NotNull
    private int walletAmount;

    @NotBlank
    @Size(max = 11)
    private String phoneNumber;

    public CustomerSignupRequest() {
    }

    public CustomerSignupRequest(String username, String email, String password, int walletAmount, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.walletAmount = walletAmount;
        this.phoneNumber = phoneNumber;
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

    public int getWalletAmount() {
        return this.walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
