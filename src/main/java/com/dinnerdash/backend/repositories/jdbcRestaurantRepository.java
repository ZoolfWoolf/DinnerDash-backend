package com.dinnerdash.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dinnerdash.backend.models.Restaurant;

@Repository
public class jdbcRestaurantRepository implements RestaurantRepository {
    // Autowired means we dont have to make a new class for db. In that case
    // This class would depend on that class and need it to work. Autowired
    // will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(Restaurant res) {
        return db.update(
                "Insert into Restaurant (RestaurantID, RestaurantName, ColorTheme, BannerUrl) values (?,?,?,?)",
                res.getRestaurantId(), res.getName(),
                res.getTheme(), res.getUrl());
    }

    @Override
    public int modify(Restaurant res) {
        return db.update(
            "Update Restaurant RestaurantName=?, ColorTheme=?, BannerUrl=? where RestaurantID=?",
            res.getName(),res.getTheme(), res.getUrl(), res.getRestaurantId());
    }
}
