package com.inditex.entities;

import java.io.Serializable;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="OBSTACLES")
public class Obstacle {

    @Id
    private long id;

    private int addressx;
    private int addressy;

    public Obstacle(){
	super();
    }
 
    public Obstacle(int addressx, int addressy){
        this.addressx = addressx;
        this.addressy = addressy;
    }

    public int getAddressx(){
	return this.addressx;
    }

    public int getAddressy(){
	return this.addressy;
    }

    public long getId(){
	return this.id;
    }

    public void setAddressx(int addressx){
	this.addressx= addressx;
    }

    public void setAddressy(int addressy){
	this.addressy= addressy;
    }

    public void setId(long id){
	this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Obstacle obstacle = (Obstacle) obj;
        return obstacle.getId() == this.getId() &&
            obstacle.getAddressx() == this.getAddressx() &&
            obstacle.getAddressy() == this.getAddressy();
    }

    @Override
    public int hashCode() {
	return Objects.hash(addressx, addressy);
    }
}

