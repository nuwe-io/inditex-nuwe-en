package com.inditex;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.inditex.repositories.ProductRepository;
import com.inditex.entities.Product;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ProductJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductRepository repository;

    String insertInto = "INSERT INTO products(id, name, stock) VALUES (?, ?, ? )";

    @Test
    void should_find_no_products_if_repository_is_empty(){
        Iterable<Product> products = repository.findAll();
        assertThat(products).isEmpty();
    }

    @Test
    void should_create_a_product(){
        Product product1 = new Product( "Product1" , 3);
	product1.setId(1);

        assertThat(product1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("name","Product1")
            .hasFieldOrPropertyWithValue("stock", 3);
    }

    @Test
    void should_find_all_products(){

	List<Product> productList = new ArrayList<Product>();
        Product product1 = new Product( "Product1" , 1);
        Product product2 = new Product( "Product2" , 3);
        Product product3 = new Product( "Product3" , 2);

	product1.setId(1);
	product2.setId(2);
	product3.setId(3);
	productList.add(product1);
	productList.add(product2);
	productList.add(product3);

	for (Product product: productList){
	    jdbcTemplate.update(insertInto, product.getId(),  product.getName(), product.getStock());
	}

        List<Product> products = repository.findAll();

        assertThat(products).hasSize(3).containsAll(productList);
    }

    @Test
    void should_find_product_by_id(){
        Product product1 = new Product( "Product1" , 1);
        Product product2 = new Product( "Product2" , 3);
	List<Product> productList = new ArrayList<Product>();
	product1.setId(1);
	product2.setId(2);

	productList.add(product1);
	productList.add(product2);

	for (Product product: productList){
	    jdbcTemplate.update(insertInto, product.getId(),  product.getName(), product.getStock());
	}

        Optional<Product> optFoundProduct = repository.findById(product2.getId());
	Product foundProduct = optFoundProduct.get();

        assertThat(foundProduct).isEqualTo(product2);
    }

    @Test
    void should_delete_product(){
        Product product1 = new Product( "Product1" , 1);
        Product product2 = new Product( "Product2" , 3);
        Product product3 = new Product( "Product3" , 2);

	product1.setId(1);
	product2.setId(2);
	product3.setId(3);

	List<Product> productList = new ArrayList<Product>();

	productList.add(product1);
	productList.add(product2);
	productList.add(product3);

	for (Product product: productList){
	    jdbcTemplate.update(insertInto, product.getId(), product.getName(), product.getStock());
	}

        repository.deleteById(product2.getId());

        Iterable<Product> products = repository.findAll();

        assertThat(products).hasSize(2).contains(product1, product3);
    }

    @Test
    void should_delete_all_products(){
        Product product1 = new Product( "Product1" , 1);
        Product product2 = new Product( "Product2" , 3);
        Product product3 = new Product( "Product3" , 2);

	product1.setId(1);
	product2.setId(2);
	product3.setId(3);

	List<Product> productList = new ArrayList<Product>();

	productList.add(product1);
	productList.add(product2);
	productList.add(product3);

	for (Product product: productList){
	    jdbcTemplate.update(insertInto, product.getId(), product.getName(), product.getStock());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
