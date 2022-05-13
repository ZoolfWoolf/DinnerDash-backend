package com.dinnerdash.backend.models;

public class Customer {
    private int CustomerID;
    private String CustomerName;
    private String Username;
    private String Password;
    private int WalletAmount;
    private String PhoneNumber;
    private String Email;

    
    Customer(){
    }

    Customer(int CustomerID,String CustomerName, String UserName, String Password, int WalletAmout, String PhoneNumber, String Email){
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.Username = UserName;
        this.Password = Password;
        this.WalletAmount = WalletAmout;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
    }

    public int getCustomerID() {
        return this.CustomerID;
    }


    public String getCustomerName() {
        return this.CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String Userame) {
        this.Username = Userame;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    }

    @Override
    public String toString(){
        return "Customer [CustomerID=" + CustomerID + ", CustomerName= " + CustomerName + ", UserName= " + Username + ", Password= " + Password + ", WalletAmount= " + WalletAmount + ", PhoneNumber= " + PhoneNumber + ", Email= " + Email + "]";
    }
}
