package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Cart;
import com.dinnerdash.backend.payload.request.AddToCartRequest;
import com.dinnerdash.backend.repositories.CartRepository;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('CUSTOMER')")
public class CartController {
    @Autowired
    CartRepository cartRepository; //Will automatically attach an object which implements this interface.

    @GetMapping(value="/getAllItems")
    public ResponseEntity<List<Cart>> getAllItems() {
        //Getting the ID of the logged in user.
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return new ResponseEntity<>(cartRepository.findByCustomer(userId), HttpStatus.OK);
    }

    @PostMapping(value="/removeItem/{offeringId}")
    public ResponseEntity<Integer> removeItem(@PathVariable("offeringId") int offeringId){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        System.out.println(offeringId);
        int response = 0;
        try{
            response = cartRepository.remove(userId, offeringId);
        }
        catch(Exception e){
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/addToCart")
    public int addItem(@RequestBody AddToCartRequest addToCart){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return cartRepository.save(new Cart(userId, addToCart.getOfferingID(), addToCart.getQuantity()));
    }

}
