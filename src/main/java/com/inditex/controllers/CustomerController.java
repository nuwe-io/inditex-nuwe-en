package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository CustomerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();

        CustomerRepository.findAll().forEach(customers::add);

        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
        Optional<Customer> customer = CustomerRepository.findById(id);
        if (! customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    }

    @GetMapping("/customers/x/{addressx}")
    public ResponseEntity<List<Customer>> getCustomerByX(@PathVariable("addressx") int addressx) {
        List<Customer> customers = new ArrayList<>();
        CustomerRepository.findByAddressx(addressx).forEach(customers::add);

        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/y/{addressy}")
    public ResponseEntity<List<Customer>> getCustomerByY(@PathVariable("addressy") int addressy) {
        List<Customer> customers = new ArrayList<>();
        CustomerRepository.findByAddressy(addressy).forEach(customers::add);

        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer client){
        Customer c = new Customer(client.getName(), client.getAddressx(), client.getAddressy());
        CustomerRepository.save(c);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id){
        Optional<Customer> customer = CustomerRepository.findById(id);

        if (! customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomers(){
        CustomerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
