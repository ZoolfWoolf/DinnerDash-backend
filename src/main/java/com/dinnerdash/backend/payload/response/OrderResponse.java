package com.dinnerdash.backend.payload.response;

import java.util.List;

import com.dinnerdash.backend.models.OrderItems;
import com.dinnerdash.backend.models.Orders;

public class OrderResponse {
    private Orders order;
    private List<OrderItems> items;


    public OrderResponse() {
    }


    public OrderResponse(Orders order, List<OrderItems> items) {
        this.order = order;
        this.items = items;
    }
    
    public Orders getOrder() {
        return this.order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<OrderItems> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }
   


}
