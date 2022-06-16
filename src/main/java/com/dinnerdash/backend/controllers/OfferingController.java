package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Offering;
import com.dinnerdash.backend.repositories.OfferingRepository;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offering")
public class OfferingController {
    @Autowired
    OfferingRepository offeringRepository; //Will automatically attach an object which implements this interface.

    @GetMapping(value="/getAllOfferings/{restaurantId}")
    public ResponseEntity<List<Offering>> getAllItems(@PathVariable("restaurantId") int restaurantId) {
        //Getting the ID of the logged in user.
        System.out.println("GET ALL OFFERINGS");
        //int restaurantId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new ResponseEntity<>(offeringRepository.findByRestaurant(restaurantId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value="/removeOffering/{offeringId}")
    public ResponseEntity<Integer> removeItem(@PathVariable("offeringId") int offeringId){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(offeringId);
        int response = 0;
        try{
            response = offeringRepository.remove(userId, offeringId);
        }
        catch(Exception e){
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value="/addOffering")
    public ResponseEntity<Offering> addItem(@RequestBody Offering addToCart){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(addToCart.getOfferingDescription());
        System.out.println(userId);

        Offering temp = new Offering(userId, addToCart.getOfferingName(), addToCart.getOfferingDescription(),addToCart.getPrice(), addToCart.getOfferingPhotoUrl());
        try{
            offeringRepository.save(temp);
        }
        catch(Exception e){
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping(value="/modifyOffering")
    public ResponseEntity<Integer> modifyItem(@RequestBody Offering addToCart){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(addToCart.getOfferingDescription());
        System.out.println(userId);

        int res = 0;
        Offering temp = new Offering(userId, addToCart.getOfferingName(), addToCart.getOfferingDescription(),addToCart.getPrice(), addToCart.getOfferingPhotoUrl());
        temp.setOfferingId(addToCart.getOfferingId());
        try{
            res = offeringRepository.modify(temp);
        }
        catch(Exception e){
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
