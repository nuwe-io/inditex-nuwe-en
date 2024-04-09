package com.inditex.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name="ORDERS")
public class Order{
    @Id
    private long id;

    private long productid;
    private long customerid;
    private long lockerid;

    public Order(){
	super();
    }
 
    public Order( long productid, long customerid, long lockerid) {
	    this.productid = productid;
	    this.customerid = customerid;
	    this.lockerid = lockerid;
    }

    public long getId(){
	return this.id;
    }

    public void setId(long id){
	this.id = id;
    }

    public void setProductid(long id) {
	this.productid = id;
    }

    public void setCustomerid(long id) {
	this.customerid = id;
    }

    public void setLockerid(long id) {
	this.lockerid = id;
    }

    public long getProductid(){
	return this.productid;
    }

    public long getCustomerid(){
	return this.customerid;
    }

    public long getLockerid(){
	return this.lockerid;
    }

    public static double distanceBetweenClientLocker(Customer c, Locker l){
        // TODO: Phase 2. Complete order entity.
	return distance;
    }

    @Override
    public boolean equals(Object obj) {
	Order order = (Order) obj;
	return order.getId() == this.getId() &&
        order.getProductid() == this.getProductid() &&
        order.getCustomerid() == this.getCustomerid() &&
        order.getLockerid() == this.getLockerid();
    }

    @Override
    public int hashCode() {
	return Objects.hash(productid, customerid, lockerid);
    }
}
