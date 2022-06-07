package com.dinnerdash.backend.models;

public class Cart {
    private int CustomerID;
    private int OfferingID;
    private int Quantity;

    Cart(){
    }

    public Cart(int CustomerID, int OfferingID, int Quantity){
        this.CustomerID = CustomerID;
        this.OfferingID = OfferingID;
        this.Quantity = Quantity;
    }

    public int getCustomerID() {
        return this.CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getOfferingID() {
        return this.OfferingID;
    }

    public void setOfferingID(int OfferingID) {
        this.OfferingID = OfferingID;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
}
