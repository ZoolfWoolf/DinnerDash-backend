package com.dinnerdash.backend.repositories;

import com.dinnerdash.backend.models.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcCustomerRepository implements CustomerRepository{
    //Autowired means we dont have to make a new class for db. In that case
    //This class would depend on that class and need it to work. Autowired 
    //will automatically attach a class with db at runtime.
    @Autowired
    private JdbcTemplate db;

    @Override
    public int save(Customer customer) {
        return db.update("Insert into Customer (CustomerID, CustomerName, UserName, Password, WalletAmount, PhoneNumber, Email) values (?,?,?,?,?,?,?)",
        customer.getCustomerID(), customer.getCustomerName(), customer.getUsername(), customer.getPassword(), customer.getWalletAmount(), customer.getPhoneNumber(), customer.getEmail());
    }

    @Override
    public Customer findById(int id) {
        //Will retuen an object of the customer model with the same id. 
        //BeanMapper maps the databse tables attributes to the class variables using setters automatically.
        return db.queryForObject("Select * from Customer where id=?", BeanPropertyRowMapper.newInstance(Customer.class), id);
    }

    @Override
    public List<Customer> findAll() {
        return db.query("Select * from Customer", BeanPropertyRowMapper.newInstance(Customer.class));
    }
    
}
