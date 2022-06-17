package com.dinnerdash.backend.repositories;

import java.util.List;

import com.dinnerdash.backend.models.Customer;

//All the Customer realated functions.
public interface CustomerRepository {
    int save(Customer customer);

    Customer findById(int id);

    int modify(Customer id);

    List<Customer> findAll();

    int addMoney(int id, int amount);

    int removeMoney(int id, int amount);
}
