package com.dinnerdash.backend.repositories;

import com.dinnerdash.backend.models.Restaurant;

//All the Customer realated functions.
public interface RestaurantRepository {
    int save(Restaurant res);
    int modify(Restaurant res);
}
