package com.example.demo.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.*;
@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private int cost;
@ManyToMany(mappedBy="products")
private List<Order> orders = new ArrayList<>();
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public List<Order> getOrders() { return orders; }
public void setOrders(List<Order> orders) { this.orders = orders; }

}
