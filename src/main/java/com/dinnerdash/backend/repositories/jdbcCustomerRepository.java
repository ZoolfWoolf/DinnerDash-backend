package com.dinnerdash.backend.repositories;

import com.dinnerdash.backend.models.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcCustomerRepository implements CustomerRepository {
    // Autowired means we dont have to make a new class for db. In that case
    // This class would depend on that class and need it to work. Autowired
    // will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(Customer customer) {
        return db.update("Insert into Customer (CustomerID, WalletAmount, PhoneNumber) values (?,?,?)",
                customer.getCustomerID(), customer.getWalletAmount(), customer.getPhoneNumber());
    }

    @Override
    public Customer findById(int id) {
        // Will retuen an object of the customer model with the same id.
        // BeanMapper maps the databse tables attributes to the class variables using
        // setters automatically.
        return db.queryForObject("Select * from Customer where CustomerID=?",
                BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    @Override
    public List<Customer> findAll() {
        return db.query("Select * from Customer", BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int addMoney(int id, int amount) {
        return db.update("Update Customer set WalletAmount = WalletAmount + ? where CustomerID=?", amount, id);
    }

    @Override
    public int removeMoney(int id, int amount) {
        int money = db.queryForObject("Select WalletAmount From Customer where CustomerID=?", Integer.class, id);
        if (money <= amount) {
            money = 0;
        }
        else {
            money = money - amount;
        }
        return db.update("Update Customer set WalletAmount = ? where CustomerID=?", money, id);
    }

    @Override
    public int modify(Customer customer) {
        return db.update("Update Customer set WalletAmount = ?, PhoneNumber = ? where CustomerID=?",
                customer.getWalletAmount(), customer.getPhoneNumber(), customer.getCustomerID());
    }

}
