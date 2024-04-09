package com.inditex.entities;


import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="LOCKERS")
public class Locker{

    @Id
    private long id;

    private int addressx;
    private int addressy;

    public Locker(){
	super();
    }
 
    public Locker(int addressx, int addressy){
        this.addressx = addressx;
        this.addressy = addressy;
    }

    public long getId(){
	return this.id;
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

    public void setAddressx(int addressx){
	this.addressx= addressx;
    }

    public void setAddressy(int addressy){
	this.addressy= addressy;
    }

    @Override
    public boolean equals(Object obj) {
        Locker locker = (Locker) obj;
        return locker.getId() == this.getId() &&
            locker.getAddressx() == this.getAddressx() &&
            locker.getAddressy() == this.getAddressy();
    }

    @Override
    public int hashCode() {
	return Objects.hash(addressx, addressy);
    }
}
