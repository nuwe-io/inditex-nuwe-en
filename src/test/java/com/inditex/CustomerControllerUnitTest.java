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

import com.inditex.controllers.CustomerController;
import com.inditex.repositories.*;
import com.inditex.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerUnitTest{

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCustomer() throws Exception {
	Customer lock = new Customer("Kiwi", 13, 12);
	mockMvc.perform(post("/api/customer").contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(lock)))
			.andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoCustomers() throws Exception{
        List<Customer> customers = new ArrayList<Customer>();
        when(customerRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoCustomers() throws Exception{
	Customer l1 = new Customer("Strawberry", 13, 12);
	Customer l2 = new Customer("Kiwi", 3, 23);

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(l1);
        customers.add(l2);

        when(customerRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
                
    }
    @Test
    void shouldGetCustomerById() throws Exception {
	Customer l1 = new Customer("Cake",10, 3);
	l1.setId(1);

	Optional<Customer> opt = Optional.of(l1);

	assertThat(opt).isPresent();
	assertThat(opt.get().getId()).isEqualTo(l1.getId());
	assertThat(l1.getId()).isEqualTo(1);

        when(customerRepository.findById(l1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/customers/" + l1.getId()))
                .andExpect(status().isOk());

    }
    
    @Test
    void shouldNotGetAnyCustomersById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/customers/" + id))
                .andExpect(status().isNotFound());
    }
}

