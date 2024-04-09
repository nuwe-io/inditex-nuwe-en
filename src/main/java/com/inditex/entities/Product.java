package com.inditex.entities;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="PRODUCTS")
public class Product {

    @Id
    private long id;

    private String name;
    private int stock;

    public Product(){
	super();
    }
 
    public Product(String name,int stock){
        this.name = name;
        this.stock = stock;
    }

    public long getId(){
	return this.id;
    }

    public String getName(){
	return this.name;
    }

    public int getStock(){
	return this.stock;
    }

    public void setStock(int stock){
	this.stock = stock;
    }

    public void setName(String name){
	this.name= name;
    }

    public void setId(long id){
	this.id= id;
    }

    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj;
        return product.getId() == this.getId() &&
            product.getName() == this.getName() &&
            product.getStock() == this.getStock();
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.name, this.stock);
    }
    
}
