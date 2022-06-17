package com.dinnerdash.backend.models;

public class Customer {
    public int id;
    private String PhoneNumber;
    private int CustomerID;
    private int WalletAmount;

    Customer() {
    }

    public Customer(int CustomerID, int WalletAmout, String PhoneNumber) {
        this.id = CustomerID;
        this.CustomerID = CustomerID;
        this.WalletAmount = WalletAmout;
        this.PhoneNumber = PhoneNumber;
    }

    public int getCustomerID() {
        return this.CustomerID;
    }

    public int getWalletAmount() {
        return this.WalletAmount;
    }

    public void setWalletAmount(int WalletAmount) {
        this.WalletAmount = WalletAmount;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
        this.id = CustomerID;
    }
}
