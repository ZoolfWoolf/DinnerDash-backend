package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Customer;
import com.dinnerdash.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('CUSTOMER')")
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

    @GetMapping("/getUser")
    public ResponseEntity<Customer> getCustomerbyID(){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Customer c = customerRepository.findById(userId);
        if (c != null){
            return new ResponseEntity<Customer>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/removeMoney/{Amount}")
    public int removeMoney(@PathVariable("Amount") int amount){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return customerRepository.removeMoney(userId, amount);
    }

    @PostMapping("/addMoney/{Amount}")
    public int addMoney(@PathVariable("Amount") int amount){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return customerRepository.addMoney(userId, amount);
    }
}
