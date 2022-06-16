package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Restaurant;
import com.dinnerdash.backend.repositories.RestaurantRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository; //Will automatically attach an object which implements this interface.

    @PostMapping("/changeDetails")
    public int changeRestaurantDetails(@RequestBody Restaurant restaurant){
        return restaurantRepository.modify(restaurant);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAll(){
        System.out.println("GET ALL Restaurants");
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);        
    }
}
