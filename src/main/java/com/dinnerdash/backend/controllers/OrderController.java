package com.dinnerdash.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.UriEncoder;

import com.dinnerdash.backend.models.Orders;
import com.dinnerdash.backend.payload.response.OrderResponse;
import com.dinnerdash.backend.repositories.OrderItemsRepository;
import com.dinnerdash.backend.repositories.OrdersRepository;
import com.dinnerdash.backend.security.services.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;


    @GetMapping("/getByCustomer/{userId}")
    public ResponseEntity<List<OrderResponse>> getByUser(@PathVariable("userId") int id){
        List<Orders> orders = ordersRepository.findByCustomer(id);
        List<OrderResponse> res = new ArrayList<OrderResponse>();

        orders.forEach((o) -> {
            OrderResponse temp = new OrderResponse();
            temp.setOrder(o);
            temp.setItems(orderItemsRepository.findByOrder(o.getOrderId()));
            res.add(temp);
        });

        return new ResponseEntity<List<OrderResponse>>(res, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getByOrder(@PathVariable("orderId") int id){
        Orders order = ordersRepository.findById(id);
        OrderResponse res = new OrderResponse();

        res.setOrder(order);
        res.setItems(orderItemsRepository.findByOrder(order.getOrderId()));

        return new ResponseEntity<OrderResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/getByRestaurant/{resId}")
    public ResponseEntity<List<OrderResponse>> getByRestaurant(@PathVariable("resId") int id){
        List<Orders> orders = ordersRepository.findByRestaurant(id);
        List<OrderResponse> res = new ArrayList<OrderResponse>();

        orders.forEach((o) -> {
            OrderResponse temp = new OrderResponse();
            temp.setOrder(o);
            temp.setItems(orderItemsRepository.findByOrder(o.getOrderId()));
            res.add(temp);
        });

        return new ResponseEntity<List<OrderResponse>>(res, HttpStatus.OK);
    }

    @PostMapping("")
    public int makeOrder(@RequestBody OrderResponse req){
        int userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Orders temp = req.getOrder();
        temp.setCustomerId(userId);
        req.setOrder(temp);

        int orderId = ordersRepository.save(req.getOrder());
        req.getItems().forEach((item) -> {
            item.setOrderId(orderId);
            orderItemsRepository.save(item);
        });

        return orderId;
    }

    @DeleteMapping("/{orderId}")
    public int removeOrder(@PathVariable("orderId") int id){
        return ordersRepository.remove(id);
    }

    //Only changes the status of the order.
    @PutMapping("/{orderId}/{status}")
    public int changeStatus(@PathVariable("orderId") int id, @PathVariable("status") String status){
        return ordersRepository.changeStatus(id, status);   
    }
}
