package com.dinnerdash.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dinnerdash.backend.models.Offering;

@Repository
public class jdbcOfferingRepository implements OfferingRepository {
    // Autowired means we dont have to make a new class for db. In that case
    // This class would depend on that class and need it to work. Autowired
    // will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int remove(int restaurantId, int offeringId) {
        return db.update("delete from Offering where OfferingID=? AND RestaurantID=?", offeringId, restaurantId);
    }

    @Override
    public int save(Offering offering) {
        return db.update(
                "Insert into Offering (OfferingID, RestaurantID, OfferingName, OfferingDescription, Price, OfferingPhotoURL) values (?,?,?,?,?,?)",
                offering.getOfferingID(), offering.getRestaurantID(), offering.getOfferingName(),
                offering.getOfferingDescription(), offering.getPrice(), offering.getUrl());
    }

    @Override
    public List<Offering> findByRestaurant(int restaurantId) {
        return db.query("Select * from Offering where RestaurantID=?",
                BeanPropertyRowMapper.newInstance(Offering.class), restaurantId);
    }

    @Override
    public int modify(Offering offering) {
        return db.update("update Offering set OfferingName=?, OfferingDescription=?, Price=?, OfferingPhotoURL=? where OfferingID=?, RestaurantID=?", 
        offering.getOfferingName(), offering.getOfferingDescription(), offering.getPrice(), offering.getUrl(), offering.getOfferingID(), offering.getRestaurantID());
    }

}
