package com.inditex.controllers;

import com.inditex.repositories.*;
import com.inditex.entities.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ObstacleController {

    @Autowired
    ObstacleRepository obstacleRepository;

    @GetMapping("/obstacles")
    public ResponseEntity<List<Obstacle>> getAllObstacles(){
        List<Obstacle> obstacles = new ArrayList<>();

        obstacleRepository.findAll().forEach(obstacles::add);

        if (obstacles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstacles, HttpStatus.OK);
    }


    @GetMapping("/obstacles/id")
    public ResponseEntity<Obstacle> getObstacleByXY(@RequestParam int x, @RequestParam int y) {
        Optional<Obstacle> obstacle = obstacleRepository.findByAddressxAndAddressy(x,y);
        if (!obstacle.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(obstacle.get(), HttpStatus.OK);

    }

    @GetMapping("/obstacles/x/{addressx}")
    public ResponseEntity<List<Obstacle>> getObstacleByx(@PathVariable("addressx") int addressx) {
        List<Obstacle> obstacles = new ArrayList<>();
        obstacleRepository.findByAddressx(addressx).forEach(obstacles::add);

        if (obstacles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstacles, HttpStatus.OK);
    }

    @GetMapping("/obstacles/y/{addressy}")
    public ResponseEntity<List<Obstacle>> getObstacleByY(@PathVariable("addressy") int addressy) {
        List<Obstacle> obstacles = new ArrayList<>();
        obstacleRepository.findByAddressy(addressy).forEach(obstacles::add);

        if (obstacles.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(obstacles, HttpStatus.OK);
    }

    @PostMapping("/obstacle")
    public ResponseEntity<Obstacle> createObstacle(@RequestBody Obstacle obstacle){
        Obstacle obs = new Obstacle(obstacle.getAddressx(), obstacle.getAddressy());
        obstacleRepository.save(obs);
        return new ResponseEntity<>(obs, HttpStatus.CREATED);
    }

    @DeleteMapping("/obstacles/{addressx}/{addressy}")
    public ResponseEntity<HttpStatus> deleteObstacle(@PathVariable("addressx") int addressx, @PathVariable("addressy") int addressy){
        Optional<Obstacle> obstacle = obstacleRepository.findByAddressxAndAddressy(addressx, addressy);

        if (! obstacle.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        obstacleRepository.delete(obstacle.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/obstacles")
    public ResponseEntity<HttpStatus> deleteAllObstacles(){
        obstacleRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
