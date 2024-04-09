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

import com.inditex.controllers.ObstacleController;
import com.inditex.repositories.*;
import com.inditex.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ObstacleController.class)
class ObstacleControllerUnitTest{

    @MockBean
    private ObstacleRepository obstacleRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateObstacle() throws Exception {
	Obstacle obs = new Obstacle(13, 12);
	mockMvc.perform(post("/api/obstacle").contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(obs)))
			.andExpect(status().isCreated());
    }
    
    @Test
    void shouldGetNoObstacles() throws Exception{
        List<Obstacle> obstacles = new ArrayList<Obstacle>();
        when(obstacleRepository.findAll()).thenReturn(obstacles);
        mockMvc.perform(get("/api/obstacles"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTwoObstacles() throws Exception{
	Obstacle o1 = new Obstacle(13, 12);
	Obstacle o2 = new Obstacle(3, 23);

        List<Obstacle> obstacles = new ArrayList<Obstacle>();
        obstacles.add(o1);
        obstacles.add(o2);

        when(obstacleRepository.findAll()).thenReturn(obstacles);
        mockMvc.perform(get("/api/obstacles"))
                .andExpect(status().isOk());
                
    }
    @Test
    void shouldGetObstacleById() throws Exception {
	Obstacle o1 = new Obstacle(10, 3);

	Optional<Obstacle> opt = Optional.of(o1);

	assertThat(opt).isPresent();
	assertThat(opt.get().getAddressx()).isEqualTo(o1.getAddressx());
	assertThat(opt.get().getAddressy()).isEqualTo(o1.getAddressy());
	assertThat(o1.getAddressx()).isEqualTo(10);
	assertThat(o1.getAddressy()).isEqualTo(3);

	String url = "/api/obstacles/id?x=" + o1.getAddressx() + "&y=" + o1.getAddressy();
        when(obstacleRepository.findByAddressxAndAddressy(o1.getAddressx(), o1.getAddressy())).thenReturn(opt);
        mockMvc.perform(get(url))
	    .andExpect(status().isOk());

    }
    
    @Test
    void shouldNotGetAnyObstaclesById() throws Exception{
	String url = "/api/obstacles/id?x=1&y=1";
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound());
    }
}

