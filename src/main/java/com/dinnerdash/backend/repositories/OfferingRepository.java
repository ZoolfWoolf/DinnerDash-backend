package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Offering;


//All the Customer realated functions.
public interface OfferingRepository {
    int save(Offering offering);
    List<Offering> findByRestaurant(int customerId);
    int remove(int restaurantId, int offeringId);   
    int modify(Offering offering);
}
