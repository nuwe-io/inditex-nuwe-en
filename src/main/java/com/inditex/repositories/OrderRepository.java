package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Order;
import com.inditex.entities.Locker;
import com.inditex.entities.Customer;
import com.inditex.entities.Product;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    // TODO: Phase 1 -> Implement SQL queries in the following methods.

    @Query("")
    List<Order> findAll();
    @Query("")
    Optional<Order> findById(@Param("id") long id);
    Order save(@Param("order") Order order);
    void delete(Order order);
    void deleteAll();

    @Query("")
    List<Order> findByNameProduct(@Param("name") String name);

    @Query("")
    List<Order> findByNameCustomer(@Param("name") String name);

    @Query("")
    List<Order> findByNameCustomerAndNameProduct(@Param("nameCustomer") String nameCustomer, @Param("nameProduct") String nameProduct);

}
