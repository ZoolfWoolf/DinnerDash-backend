package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Restaurant;

//All the Customer realated functions.
public interface RestaurantRepository {
    int save(Restaurant res);
    int modify(Restaurant res);
    List<Restaurant> findAll();
}
