package com.inditex;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.inditex.controllers.OrderController;
import com.inditex.repositories.*;
import com.inditex.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
class OrderControllerUnitTest{

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private LockerRepository lockerRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateOrder() throws Exception {
	Locker locker1 = new Locker(2,1); 
	Locker locker2 = new Locker(2,2); 
	Product product = new Product("Tshirt", 1); 
	Customer customer = new Customer("Jose", 3,2); 

	List<Locker> lockers = new ArrayList<>();
	lockers.add(locker1);
	lockers.add(locker2);

	when(lockerRepository.findAll()).thenReturn(lockers);
	when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
	when(customerRepository.findById(customer.getId())).thenReturn(Optional.ofNullable(customer));

	String url ="/api/order?productid=" + product.getId()
	    + "&customerid="  + customer.getId();
	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateOrder() throws Exception {
	Locker locker1 = new Locker(2,1); 
	Locker locker2 = new Locker(2,2); 
	Product product = new Product("Tshirt", 0); 
	Customer customer = new Customer("Jose", 3,2); 

	List<Locker> lockers = new ArrayList<>();
	lockers.add(locker1);
	lockers.add(locker2);

	when(lockerRepository.findAll()).thenReturn(lockers);

	String url ="/api/order?productid=" + product.getId()
	    + "&customerid="  + customer.getId();
	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());


	when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));

	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

	when(customerRepository.findById(customer.getId())).thenReturn(Optional.ofNullable(customer));

	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
	    .andExpect(status().isPreconditionFailed());
    }

    @Test
    void shouldCreateMultiplesOrder() throws Exception {
	Locker locker1 = new Locker(2,1); 
	Locker locker2 = new Locker(2,2); 
	Locker locker3 = new Locker(20,32); 
	Product product1 = new Product("Tshirt", 2); 
	Product product2 = new Product("Bag", 10); 
	Customer customer1 = new Customer("Jose", 3,2); 
	Customer customer2 = new Customer("Paula", 4,4); 
	Customer customer3 = new Customer("Juan", 6,3); 
	Customer customer4 = new Customer("Natalia", 8,2); 

	List<Locker> lockers = new ArrayList<>();
	lockers.add(locker1);
	lockers.add(locker2);
	lockers.add(locker3);

	List<Product> products = new ArrayList<>();
	products.add(product1);
	products.add(product2);

	List<Customer> customers = new ArrayList<>();
	customers.add(customer1);
	customers.add(customer2);
	customers.add(customer3);
	customers.add(customer4);

	when(lockerRepository.findAll()).thenReturn(lockers);
	when(productRepository.findAll()).thenReturn(products);
	when(customerRepository.findAll()).thenReturn(customers);

	when(productRepository.findById(product1.getId())).thenReturn(Optional.ofNullable(product1));
	when(productRepository.findById(product2.getId())).thenReturn(Optional.ofNullable(product2));

	when(customerRepository.findById(customer1.getId())).thenReturn(Optional.ofNullable(customer1));
	when(customerRepository.findById(customer2.getId())).thenReturn(Optional.ofNullable(customer2));

	String url ="/api/order?productid=" + product1.getId()
	    + "&customerid="  + customer1.getId();

	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());

	url ="/api/order?productid=" + product2.getId()
	    + "&customerid="  + customer2.getId();

	mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());

    }
    
    @Test
    void shouldGetNoOrders() throws Exception{
        List<Order> orders = new ArrayList<Order>();
        when(orderRepository.findAll()).thenReturn(orders);
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoOrders() throws Exception{
	Locker locker1 = new Locker(2,1); 
	Locker locker2 = new Locker(2,2); 
	Product product = new Product("Tshirt", 2); 
	Customer customer1 = new Customer("Jose", 3,2); 
	Customer customer2 = new Customer("Paula", 8,2); 

	Order p1 = new Order(product.getId(), customer1.getId(), locker2.getId());
	Order p2 = new Order(product.getId(), customer2.getId(), locker2.getId());

	List<Order> orders = new ArrayList<>();
	orders.add(p1);
	orders.add(p2);
	    

        when(orderRepository.findAll()).thenReturn(orders);
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }
    
    @Test
    void shouldNotGetAnyOrdersById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/orders/" + id))
                .andExpect(status().isNotFound());
    }
}

