package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Customer;
import com.dinnerdash.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository; //Will automatically attach an object which implements this interface.

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Customer> getCustomerbyID(@PathVariable("id") int id){
        Customer c = customerRepository.findById(id);
        if (c != null){
            return new ResponseEntity<Customer>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
