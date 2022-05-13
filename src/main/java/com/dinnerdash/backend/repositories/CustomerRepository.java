package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Customer;

//All the Customer realated functions.
public interface CustomerRepository {
    int save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
}
