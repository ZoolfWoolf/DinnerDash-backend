package com.dinnerdash.backend.models;

public class OrderItems {
    private int orderId;
    private int offeringId;
    private int quantity;
    private int price;

    public OrderItems() {
    }

    public OrderItems(int orderId, int offeringId, int quantity, int price) {
        this.orderId = orderId;
        this.offeringId = offeringId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}