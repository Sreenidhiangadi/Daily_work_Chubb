package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="review")

public class Review {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String comment;
	   @ManyToOne
	   @JsonBackReference
	    @JoinColumn(name = "order_id")  
	    private Order order;
	public Order getOrder() {
		return order;
	}
	   public void setOrder(Order order) {
		   this.order = order;
	   }
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
