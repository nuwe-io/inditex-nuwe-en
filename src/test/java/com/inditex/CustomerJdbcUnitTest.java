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

import com.inditex.repositories.CustomerRepository;
import com.inditex.entities.Customer;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class CustomerJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerRepository repository;

    String insertInto = "INSERT INTO customers (id, name, addressx, addressy) VALUES (?, ?, ?, ? )";

    @Test
    void should_find_no_customers_if_repository_is_empty(){
        Iterable<Customer> customers = repository.findAll();
        assertThat(customers).isEmpty();
    }

    @Test
    void should_create_a_cliente(){
        Customer cliente1 = new Customer("CustomerUno",  1, 3);
	cliente1.setId(1);

        assertThat(cliente1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("name", "CustomerUno")
            .hasFieldOrPropertyWithValue("addressx", 1)
            .hasFieldOrPropertyWithValue("addressy", 3);
    }

    @Test
    void should_find_all_customers(){

	List<Customer> cs = new ArrayList<Customer>();
        Customer cliente1 = new Customer( "CustomerUno", 1 , 1);
        Customer cliente2 = new Customer( "CustomerDos", 2, 3);
        Customer cliente3 = new Customer( "CustomerTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);
	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Customer c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getName(), c.getAddressx(), c.getAddressy());
	}

        List<Customer> customers = repository.findAll();

        assertThat(customers).hasSize(3).containsAll(cs);
    }

    @Test
    void should_find_cliente_by_id(){
        Customer cliente1 = new Customer( "CustomerUno",  1, 1);
        Customer cliente2 = new Customer( "CustomerDos",  2, 3);
	List<Customer> cs = new ArrayList<Customer>();
	cliente1.setId(1);
	cliente2.setId(2);

	cs.add(cliente1);
	cs.add(cliente2);

	for (Customer c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getName(), c.getAddressx(), c.getAddressy());
	}

        Optional<Customer> optFoundCustomer = repository.findById(cliente2.getId());
	Customer foundCustomer = optFoundCustomer.get();

        assertThat(foundCustomer).isEqualTo(cliente2);
    }

    @Test
    void should_delete_cliente(){
        Customer cliente1 = new Customer( "CustomerUno",  1, 1);
        Customer cliente2 = new Customer( "CustomerDos",  2, 3);
        Customer cliente3 = new Customer( "CustomerTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);

	List<Customer> cs = new ArrayList<Customer>();

	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Customer c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getName(), c.getAddressx(), c.getAddressy());
	}

        repository.deleteById(cliente2.getId());

        Iterable<Customer> customers = repository.findAll();

        assertThat(customers).hasSize(2).contains(cliente1, cliente3);
    }

    @Test
    void should_delete_all_customers(){
        Customer cliente1 = new Customer("CustomerUno",  1, 1);
        Customer cliente2 = new Customer("CustomerDos",  2, 3);
        Customer cliente3 = new Customer("CustomerTres", 3, 2);

	cliente1.setId(1);
	cliente2.setId(2);
	cliente3.setId(3);

	List<Customer> cs = new ArrayList<Customer>();

	cs.add(cliente1);
	cs.add(cliente2);
	cs.add(cliente3);

	for (Customer c: cs){
	    jdbcTemplate.update(insertInto, c.getId(), c.getName(), c.getAddressx(), c.getAddressy());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
