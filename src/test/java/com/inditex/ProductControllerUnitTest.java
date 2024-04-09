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

import com.inditex.controllers.ProductController;
import com.inditex.repositories.*;
import com.inditex.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
class ProductControllerUnitTest{

    @MockBean
    private ProductRepository productRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProduct() throws Exception {
	Product prod = new Product("TestProduct", 12);
	mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(prod)))
			.andExpect(status().isCreated());
    }

    @Test
    void shouldGetNoProducts() throws Exception{
        List<Product> prods= new ArrayList<Product>();
        when(productRepository.findAll()).thenReturn(prods);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoProducts() throws Exception{
	Product p1 = new Product("ProductUno", 12);
	Product p2 = new Product("ProductDos", 13);

        List<Product> prods = new ArrayList<Product>();
        prods.add(p1);
        prods.add(p2);

        when(productRepository.findAll()).thenReturn(prods);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
                
    }
    @Test
    void shouldGetProductById() throws Exception {
	Product p1 = new Product("ProductUno", 12);
	p1.setId(1);

	Optional<Product> opt = Optional.of(p1);

	assertThat(opt).isPresent();
	assertThat(opt.get().getId()).isEqualTo(p1.getId());
	assertThat(p1.getId()).isEqualTo(1);

        when(productRepository.findById(p1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/products/" + p1.getId()))
                .andExpect(status().isOk());

    }
    
    @Test
    void shouldNotGetAnyProductsById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/products/" + id))
                .andExpect(status().isNotFound());
    }
}

