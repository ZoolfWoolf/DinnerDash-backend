package com.dinnerdash.backend.payload.response;

public class UserResponse {
    private String username;
    private String email;
    private String phoneNumber;
    private int walletAmount;

    public UserResponse() {
    }

    public UserResponse(String username, String email, String phoneNumber, int walletAmount) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.walletAmount = walletAmount;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getWalletAmount() {
        return this.walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }

}
