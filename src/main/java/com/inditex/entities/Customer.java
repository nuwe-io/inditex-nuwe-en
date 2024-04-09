package com.inditex.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="CUSTOMERS")
public class Customer{

    @Id
    private long id;

    private String name;
    private int addressx;
    private int addressy;

    public Customer(){
	super();
    }
 
    public Customer(String name, int addressx, int addressy){
        this.name = name;
        this.addressx = addressx;
        this.addressy = addressy;
    }

    public long getId(){
	return this.id;
    }
    public String getName(){
	return this.name;
    }

    public int getAddressx(){
	return this.addressx;
    }

    public int getAddressy(){
	return this.addressy;
    }

    public void setId(long id){
	this.id = id;
    }

    public void setName(String name){
	this.name= name;
    }

    public void setAddressx(int addressx){
	this.addressx= addressx;
    }

    public void setAddressy(int addressy){
	this.addressy= addressy;
    }

    @Override
    public boolean equals(Object co) {
        Customer c = (Customer) co;
        return c.getId() == this.getId() &&
            c.getName() == this.getName() &&
            c.getAddressx() == this.getAddressx() &&
            c.getAddressy() == this.getAddressy();
    }

    @Override
    public int hashCode() {
	return Objects.hash(name, addressx, addressy);
    }
    
}
