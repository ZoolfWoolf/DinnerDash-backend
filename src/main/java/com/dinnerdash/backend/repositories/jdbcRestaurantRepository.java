package com.dinnerdash.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                res.getRestaurantId(), res.getRestaurantName(),
                res.getColorTheme(), res.getBannerUrl());
    }

    @Override
    public int modify(Restaurant res) {
        return db.update(
                "Update Restaurant set RestaurantName=?, ColorTheme=?, BannerUrl=? where RestaurantID=?",
                res.getRestaurantName(), res.getColorTheme(), res.getBannerUrl(), res.getRestaurantId());
    }

    @Override
    public List<Restaurant> findAll() {
        return db.query("SELECT * FROM Restaurant", BeanPropertyRowMapper.newInstance(Restaurant.class));
    }

    @Override
    public Restaurant getById(int id) {
        return db.queryForObject("Select * from Restaurant where RestaurantID=?",   
                BeanPropertyRowMapper.newInstance(Restaurant.class), id);
    }

    @Override
    public int remove(int id) {
        return db.update(
                "Delete from Offering where RestaurantID=?;Delete from Restaurant where RestaurantID=?;Delete from user_roles where user_id=?;Delete from users where id=?;",
                id, id, id, id);
    }
}
