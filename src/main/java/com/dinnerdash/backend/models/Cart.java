package com.dinnerdash.backend.models;

public class Cart {
    private int customerId;
    private int offeringId;
    private int quantity;

    Cart() {
    }

    public Cart(int customerId, int offeringId, int quantity) {
        this.customerId = customerId;
        this.offeringId = offeringId;
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOfferingId() {
        return this.offeringId;
    }

    public void setOfferingId(int offeringId) {
        this.offeringId = offeringId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
