package com.dinnerdash.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dinnerdash.backend.models.Cart;

@Repository
public class jdbcCartRepository implements CartRepository{
    //Autowired means we dont have to make a new class for db. In that case
    //This class would depend on that class and need it to work. Autowired 
    //will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(Cart cart) {
        return db.update("Insert into Cart (CustomerID, OfferingID, Quantity) values (?,?,?)",
        cart.getCustomerID(), cart.getOfferingID(), cart.getQuantity());
    }

    @Override
    public List<Cart> findByCustomer(int customerId) {
        return db.query("Select * from Cart where CustomerID=?", BeanPropertyRowMapper.newInstance(Cart.class), customerId);
    }

    @Override
    public int remove(int customerId , int offeringId) {
        return db.update("delete from Cart where OfferingID=? AND CustomerID=?", offeringId, customerId);
    }
    
}
