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

import com.inditex.repositories.ObstacleRepository;
import com.inditex.entities.Obstacle;


@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class ObstacleJdbcUnitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ObstacleRepository repository;

    String insertInto = "INSERT INTO obstacles(id, addressx, addressy) VALUES (?, ?, ? )";

    @Test
    void should_find_no_obstacles_if_repository_is_empty(){
        Iterable<Obstacle> obstacles = repository.findAll();
        assertThat(obstacles).isEmpty();
    }

    @Test
    void should_create_a_obstaculo(){
        Obstacle obstaculo1 = new Obstacle( 1, 3);
	obstaculo1.setId(1);

        assertThat(obstaculo1)
            .hasFieldOrPropertyWithValue("id",1L)
            .hasFieldOrPropertyWithValue("addressx", 1)
            .hasFieldOrPropertyWithValue("addressy", 3);
    }

    @Test
    void should_find_all_obstacles(){

	List<Obstacle> obstaculoList = new ArrayList<Obstacle>();
        Obstacle obstaculo1 = new Obstacle( 1 , 1);
        Obstacle obstaculo2 = new Obstacle( 2, 3);
        Obstacle obstaculo3 = new Obstacle( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);
	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstacle obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(),  obstaculo.getAddressx(), obstaculo.getAddressy());
	}

        List<Obstacle> obstacles = repository.findAll();

        assertThat(obstacles).hasSize(3).containsAll(obstaculoList);
    }

    @Test
    void should_find_obstaculo_by_id(){
        Obstacle obstaculo1 = new Obstacle(  1, 1);
        Obstacle obstaculo2 = new Obstacle(  2, 3);
	List<Obstacle> obstaculoList = new ArrayList<Obstacle>();
	obstaculo1.setId(1);
	obstaculo2.setId(2);

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);

	for (Obstacle obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(),  obstaculo.getAddressx(), obstaculo.getAddressy());
	}

        Optional<Obstacle> optFoundObstacle = repository.findById(obstaculo2.getId());
	Obstacle foundObstacle = optFoundObstacle.get();

        assertThat(foundObstacle).isEqualTo(obstaculo2);
    }

    @Test
    void should_delete_obstaculo(){
        Obstacle obstaculo1 = new Obstacle(  1, 1);
        Obstacle obstaculo2 = new Obstacle(  2, 3);
        Obstacle obstaculo3 = new Obstacle( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);

	List<Obstacle> obstaculoList = new ArrayList<Obstacle>();

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstacle obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(), obstaculo.getAddressx(), obstaculo.getAddressy());
	}

        repository.deleteById(obstaculo2.getId());

        Iterable<Obstacle> obstacles = repository.findAll();

        assertThat(obstacles).hasSize(2).contains(obstaculo1, obstaculo3);
    }

    @Test
    void should_delete_all_obstacles(){
        Obstacle obstaculo1 = new Obstacle(  1, 1);
        Obstacle obstaculo2 = new Obstacle(  2, 3);
        Obstacle obstaculo3 = new Obstacle( 3, 2);

	obstaculo1.setId(1);
	obstaculo2.setId(2);
	obstaculo3.setId(3);

	List<Obstacle> obstaculoList = new ArrayList<Obstacle>();

	obstaculoList.add(obstaculo1);
	obstaculoList.add(obstaculo2);
	obstaculoList.add(obstaculo3);

	for (Obstacle obstaculo: obstaculoList){
	    jdbcTemplate.update(insertInto, obstaculo.getId(), obstaculo.getAddressx(), obstaculo.getAddressy());
	}

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
    
}
