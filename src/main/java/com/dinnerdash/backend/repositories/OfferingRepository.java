package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Offering;

//All the Customer realated functions.
public interface OfferingRepository {
    Offering save(Offering offering);

    List<Offering> findByRestaurant(int restaurantID);

    List<Offering> findAll();

    Offering findById(int offeringId, int restaurantID);

    Offering findById(int offeringId);

    int remove(int restaurantId, int offeringId);

    int modify(Offering offering);
}
