package com.dinnerdash.backend.models;

public class Orders {
    private int orderId;
    private int customerId;
    private int restaurantId;
    private String time;
    private String paymentMethod;
    private String status;

    public Orders() {
    }

    public Orders(int orderId, int customerId, int resId, String time, String paymentMethod, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.time = time;
        this.restaurantId = resId;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
