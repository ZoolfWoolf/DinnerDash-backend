package com.dinnerdash.backend.models;

public class Customer {
    private int CustomerID;
    private int WalletAmount;
    private String PhoneNumber;

    
    Customer(){
    }

    public Customer(int CustomerID, int WalletAmout, String PhoneNumber){
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

    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    }
}
