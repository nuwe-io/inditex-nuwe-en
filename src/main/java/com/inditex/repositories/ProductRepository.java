package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Product;

import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findById(long id);
    Product save(Product prod);
    void delete(Product prod);
    void deleteById(long id);
    void deleteAll();
}
