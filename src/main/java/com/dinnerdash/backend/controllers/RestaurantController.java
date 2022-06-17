package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Restaurant;
import com.dinnerdash.backend.repositories.RestaurantRepository;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository; // Will automatically attach an object which implements this interface.

    @PostMapping("/{id}")
    public ResponseEntity<Restaurant> changeRestaurantDetails(@RequestBody Restaurant restaurant) {
        restaurantRepository.modify(restaurant);
        return getById(restaurant.id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> changeRestaurantDetailsPut(@RequestBody Restaurant restaurant) {
        return changeRestaurantDetails(restaurant);
    }

    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAll() {
        System.out.println("GET ALL Restaurants");

        List<Restaurant> list;
        try {
            // get retaurant id
            int restaurantId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getId();
            list = new ArrayList<Restaurant>();
            list.add(restaurantRepository.getById(restaurantId));
        } catch (Exception e) {
            // TODO: handle exception
            list = restaurantRepository.findAll();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Expose-Headers", "Content-Range");
        headers.set("Content-Range", "restaurants " + list.size() + "/" + list.size());

        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAll2() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable("id") int id) {
        System.out.println("GET Restaurant By ID");
        return new ResponseEntity<>(restaurantRepository.getById(id), HttpStatus.OK);
    }
}
