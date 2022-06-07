package com.dinnerdash.backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dinnerdash.backend.models.OrderItems;

@Repository
public class jdbcOrderItemsRepository implements OrderItemsRepository {
    // Autowired means we dont have to make a new class for db. In that case
    // This class would depend on that class and need it to work. Autowired
    // will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(OrderItems order) {
        return db.update(
                "Insert into OrderItems (OrderID, OfferingID, Quantity, Price) values (?,?,?,?)",
                order.getOrderId(), order.getOfferingId(), order.getQuantity(),
                order.getPrice());
    }

    @Override
    public List<OrderItems> findByOrder(int orderId) {
        return db.query("Select * from OrderItems where OrderID=?",
                BeanPropertyRowMapper.newInstance(OrderItems.class), orderId);
    }
}
