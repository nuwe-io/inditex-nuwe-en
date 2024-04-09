package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LockerRepository lockerRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
	// TODO: Phase 2
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id){
	// TODO: Phase 2
    }

    @PostMapping("/order")
	public ResponseEntity<List<Order>> createOrder(@RequestParam long productid, @RequestParam long customerid ){
    }
	// TODO: Phase 2


    @DeleteMapping("/orders")
    public ResponseEntity<HttpStatus> deleteAllOrders(){
	// TODO: Phase 2
    }

}
