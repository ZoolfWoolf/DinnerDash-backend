package com.dinnerdash.backend.models;

public class Orders {
    private int orderId;
    private int customerId;
    private int restaurantId;
    private String orderTime;
    private String paymentMethod;
    private String orderStatus;

    public Orders() {
    }


    public Orders(int orderId, int customerId, int restaurantId, String orderTime, String paymentMethod, String orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderTime = orderTime;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
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

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
   
}
