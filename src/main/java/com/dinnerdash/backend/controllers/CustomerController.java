package com.dinnerdash.backend.controllers;

import com.dinnerdash.backend.models.Customer;
import com.dinnerdash.backend.models.Users;
import com.dinnerdash.backend.payload.response.UserResponse;
import com.dinnerdash.backend.repositories.CustomerRepository;
import com.dinnerdash.backend.repositories.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
// @PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository; // Will automatically attach an object which implements this interface.
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponse> getCustomerbyID(@PathVariable("id") int id) {
        Customer c = customerRepository.findById(id);
        Users u = userRepository.findById(id);
        System.out.println(u);
        
        if (c != null) {
            return new ResponseEntity<UserResponse>(new UserResponse(u.getUsername(), u.getEmail(), c.getPhoneNumber(), c.getWalletAmount()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getUser")
    public ResponseEntity<Customer> getCustomerbyID() {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Customer c = customerRepository.findById(userId);
        if (c != null) {
            return new ResponseEntity<Customer>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/removeMoney/{Amount}")
    public int removeMoney(@PathVariable("Amount") int amount) {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return customerRepository.removeMoney(userId, amount);
    }

    @PostMapping("/addMoney/{Amount}")
    public int addMoney(@PathVariable("Amount") int amount) {
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return customerRepository.addMoney(userId, amount);
    }

    // REST
    @PreAuthorize("hasRole('RESTAURANT')")
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAll() {
        System.out.println("GET ALL Customers");

        List<Customer> list = customerRepository.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Expose-Headers", "Content-Range");
        headers.set("Content-Range", "customers " + list.size() + "/" + list.size());

        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RESTAURANT')")
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll2() {
        return getAll();
    }

    // @PreAuthorize("hasRole('RESTAURANT')")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") int id) {
        System.out.println("GET ONE Customers " + id);

        Customer c = customerRepository.findById(id);
        if (c != null) {
            return new ResponseEntity<Customer>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") int id, @RequestBody Customer customer) {
        System.out.println("UPDATE Customers " + id);

        Customer c = customerRepository.findById(id);
        if (c != null) {
            try {
                customerRepository.modify(customer);
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
