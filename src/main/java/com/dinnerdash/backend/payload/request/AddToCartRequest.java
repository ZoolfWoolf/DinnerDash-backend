package com.dinnerdash.backend.payload.request;

public class AddToCartRequest {
    public int offeringID;
    public int quantity;

    public int getOfferingID() {
        return this.offeringID;
    }

    public void setOfferingID(int OfferingID) {
        this.offeringID = OfferingID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

}
