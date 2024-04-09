package com.inditex.repositories;

import java.util.List;
import java.util.Optional;

import com.inditex.entities.Obstacle;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

public interface ObstacleRepository extends Repository<Obstacle, Long> {
    List<Obstacle> findAll();
    Optional<Obstacle> findByAddressxAndAddressy(int addressx, int addressy);
    List<Obstacle> findByAddressx(int addressx);
    List<Obstacle> findByAddressy(int addressy);
    Optional<Obstacle> findById(long id);
    Obstacle save(Obstacle obs);
    void delete(Obstacle obs);
    void deleteById(long id);
    void deleteAll();
}
