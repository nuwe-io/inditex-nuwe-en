package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Customer;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT * FROM customers;")
    List<Customer> findAll();
    Optional<Customer> findById(long id);
    List<Customer> findByAddressx(int addressx);
    List<Customer> findByAddressy(int addressy);
    Customer save(Customer client);
    void delete(Customer client);
    void deleteById(long id);
}
