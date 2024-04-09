package com.inditex;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.inditex.repositories.*;
import com.inditex.entities.*;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class OrderJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    LockerRepository lockerRepository;

    @Autowired
    OrderRepository orderRepository;

    private Product product1;
    private Product product2;

    private Customer customer1;
    private Customer customer2;

    private Locker locker1;
    private Locker locker2;

    private String customerInsertInto = "INSERT INTO customers (id, name, addressx, addressy) VALUES (?, ?, ?, ? )";
    private String productInsertInto = "INSERT INTO products (id, name, stock) VALUES (?, ?, ? )";
    private String lockerInsertInto = "INSERT INTO lockers (id, addressx, addressy) VALUES (?, ?, ? )";
    private String orderInsertInto = "INSERT INTO orders (id, productId, customerId, lockerId) VALUES (?, ?, ?, ? )";

    @BeforeEach
    void setUp(){
        product1 = new Product("Tshirt", 3);
        product2 = new Product("Bag", 12);
	product1.setId(1);
	product2.setId(2);

        customer1 = new Customer("Paco", 1, 1);
        customer2 = new Customer("Lucia", 2, 3);
	customer1.setId(1);
	customer2.setId(2);

        locker1 = new Locker(1, 2);
        locker2 = new Locker(2, 4);
	locker1.setId(1);
	locker2.setId(2);

	List<Product> productLista = new ArrayList<Product>();
	List<Customer> customerLista = new ArrayList<Customer>();
	List<Locker> lockerLista = new ArrayList<Locker>();

	productLista.add(product1);
	productLista.add(product2);

	customerLista.add(customer1);
	customerLista.add(customer2);

	lockerLista.add(locker1);
	lockerLista.add(locker2);
	for (Product product : productLista){
	    jdbcTemplate.update(productInsertInto, product.getId(),  product.getName(), product.getStock());
	}
	for (Customer customer : customerLista){
	    jdbcTemplate.update(customerInsertInto, customer.getId(), customer.getName(), customer.getAddressx(), customer.getAddressy());
	}
	for (Locker locker : lockerLista){
	    jdbcTemplate.update(lockerInsertInto, locker.getId(),  locker.getAddressx(), locker.getAddressy());
	}
    }


    @Test
    void should_find_no_orders_if_repository_is_empty(){
        Iterable<Order> orders = orderRepository.findAll();
        assertThat(orders).isEmpty();
    }

    @Test
    void should_create_a_order(){
        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
	order1.setId(1);

        assertThat(order1)
            .hasFieldOrPropertyWithValue("id", order1.getId())
            .hasFieldOrPropertyWithValue("productid", product1.getId())
            .hasFieldOrPropertyWithValue("customerid", customer1.getId())
            .hasFieldOrPropertyWithValue("lockerid", locker1.getId());
    }

    @Test
    void should_find_all_orders(){

        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
        Order order2 = new Order(product1.getId(), customer2.getId(), locker1.getId());
        Order order3 = new Order(product2.getId(), customer2.getId(), locker1.getId());
	order1.setId(1);
	order2.setId(2);
	order3.setId(3);

	List<Order> orderList = new ArrayList<Order>();
	orderList.add(order1);
	orderList.add(order2);
	orderList.add(order3);

	for (Order order: orderList){
	    jdbcTemplate.update(orderInsertInto, order.getId(), order.getProductid(), order.getCustomerid(), order.getLockerid());
	}

        List<Order> orders = orderRepository.findAll();

        assertThat(orders).hasSize(3).containsAll(orderList);
    }

    @Test
    void should_find_order_by_id(){
        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
        Order order2 = new Order(product1.getId(), customer2.getId(), locker1.getId());
	order1.setId(1);
	order2.setId(2);
	List<Order> orderList = new ArrayList<Order>();
	orderList.add(order1);
	orderList.add(order2);

	for (Order order: orderList){
	    jdbcTemplate.update(orderInsertInto, order.getId(), order.getProductid(), order.getCustomerid(), order.getLockerid());
	}

        Order foundOrder = orderRepository.findById(order2.getId()).get();

        assertThat(foundOrder).isEqualTo(order2);
    }

    @Test
    void should_find_order_by_name_product(){

        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
        Order order2 = new Order(product1.getId(), customer2.getId(), locker1.getId());
        Order order3 = new Order(product2.getId(), customer2.getId(), locker1.getId());
	order1.setId(1);
	order2.setId(2);
	order3.setId(3);
	List<Order> orderList = new ArrayList<Order>();
	orderList.add(order1);
	orderList.add(order2);
	orderList.add(order3);

	for (Order order: orderList){
	    jdbcTemplate.update(orderInsertInto, order.getId(), order.getProductid(), order.getCustomerid(), order.getLockerid());
	}

        List<Order> foundOrders = orderRepository.findByNameProduct(product1.getName());
	assertThat(foundOrders).hasSize(2).contains(order1, order2);

        foundOrders = orderRepository.findByNameProduct(product2.getName());
	assertThat(foundOrders).hasSize(1).contains(order3);

        foundOrders = orderRepository.findByNameProduct("DoesNotExist");
	assertThat(foundOrders).hasSize(0);
    }

    @Test
    void should_find_order_by_name_customer(){
        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
        Order order2 = new Order(product1.getId(), customer2.getId(), locker2.getId());
        Order order3 = new Order(product2.getId(), customer2.getId(), locker1.getId());
	order1.setId(1);
	order2.setId(2);
	order3.setId(3);
	List<Order> orderList = new ArrayList<Order>();
	orderList.add(order1);
	orderList.add(order2);
	orderList.add(order3);

	for (Order order: orderList){
	    jdbcTemplate.update(orderInsertInto, order.getId(), order.getProductid(), order.getCustomerid(), order.getLockerid());

	}

	List<Order> foundOrders = orderRepository.findByNameCustomer(customer2.getName());
	assertThat(foundOrders).hasSize(2).contains(order2, order3);

	foundOrders = orderRepository.findByNameCustomer(customer1.getName());
	assertThat(foundOrders).hasSize(1).contains(order1);

	foundOrders = orderRepository.findByNameCustomer("DoesNotExist");
	assertThat(foundOrders).hasSize(0);
    }

    @Test
    void should_find_order_by_name_customer_and_name_product(){
        Order order1 = new Order(product1.getId(), customer1.getId(), locker1.getId());
        Order order2 = new Order(product1.getId(), customer2.getId(), locker2.getId());
        Order order3 = new Order(product2.getId(), customer2.getId(), locker1.getId());
	order1.setId(1);
	order2.setId(2);
	order3.setId(3);
	List<Order> orderList = new ArrayList<Order>();
	orderList.add(order1);
	orderList.add(order2);
	orderList.add(order3);

	for (Order order: orderList){
	    jdbcTemplate.update(orderInsertInto, order.getId(), order.getProductid(), order.getCustomerid(), order.getLockerid());

	}

	List<Order> foundOrders = orderRepository.findByNameCustomerAndNameProduct(customer2.getName(), product1.getName());
	assertThat(foundOrders).hasSize(1).contains(order2);

	foundOrders = orderRepository.findByNameCustomerAndNameProduct(customer2.getName(), product2.getName());
	assertThat(foundOrders).hasSize(1).contains(order3);

	foundOrders = orderRepository.findByNameCustomerAndNameProduct(customer1.getName(), product2.getName());
	assertThat(foundOrders).hasSize(0);
    }

}
