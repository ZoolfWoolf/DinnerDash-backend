package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Orders;

//All the Customer realated functions.
public interface OrdersRepository {
    int save(Orders order);
    List<Orders> findByRestaurant(int restaurantId);
    List<Orders> findByCustomer(int customerId);
}
