package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Cart;

//All the Customer realated functions.
public interface CartRepository {
    int save(Cart cart);
    List<Cart> findByCustomer(int customerId);
    int remove(int customerId,int offeringId);   
}
