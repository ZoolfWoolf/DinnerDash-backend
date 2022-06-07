package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Restaurant;
import com.dinnerdash.backend.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository; //Will automatically attach an object which implements this interface.

    @PostMapping("/changeDetails/")
    public int changeRestaurantDetails(@RequestBody Restaurant restaurant){
        return restaurantRepository.modify(restaurant);
    }
}
