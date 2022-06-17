package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.OrderItems;

//All the Customer realated functions.
public interface OrderItemsRepository {
    int save(OrderItems orderItem);

    List<OrderItems> findByOrder(int orderId);
}
