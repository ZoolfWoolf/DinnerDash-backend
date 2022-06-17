package com.dinnerdash.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dinnerdash.backend.models.Orders;

@Repository
public class jdbcOrdersRepository implements OrdersRepository {
    // Autowired means we dont have to make a new class for db. In that case
    // This class would depend on that class and need it to work. Autowired
    // will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(Orders order) {
        db.update(
                "Insert into Orders (CustomerID, RestaurantID, OrderTime, PaymentMethod, OrderStatus) values (?,?,?,?,?)",
                order.getCustomerId(), order.getRestaurantId(),
                order.getOrderTime(), order.getPaymentMethod(), order.getOrderStatus());
        return db.queryForObject("Select MAX(OrderID) from Orders where CustomerID=?", Integer.class, order.getCustomerId());
    }

    @Override
    public List<Orders> findByRestaurant(int restaurantId) {
        return db.query("Select * from Orders where RestaurantID=?",
                BeanPropertyRowMapper.newInstance(Orders.class), restaurantId);
    }

    @Override
    public List<Orders> findByCustomer(int custId) {
        return db.query("Select * from Orders where CustomerID=?",
                BeanPropertyRowMapper.newInstance(Orders.class), custId);
    }

}
