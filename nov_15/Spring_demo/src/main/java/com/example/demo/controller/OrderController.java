package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.Order;
import com.example.demo.request.Review;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	@Autowired
	OrderService orderService;
	@GetMapping("/order")
	Iterable<Order> getOrder() {
		System.out.println("Get Request");
		return orderService.getAllOrders();
	}
	@PostMapping("/add")
	void getOrders(@RequestBody @Valid Order hello) {
		System.out.println("Post request");
		orderService.saveOrder(hello);
	}
	@DeleteMapping("/delete/{orderId}")
		void saveOrder(@PathVariable @Valid int orderId) {
		System.out.println(orderId);
		orderService.deleteOrder(orderId);
	}
	 @PostMapping("/orders/{orderId}/reviews")
	    public Order addReview(@PathVariable int orderId, @RequestBody Review review) {
	        log.info("Add review to order {}", orderId);
	        return orderService.addReview(orderId, review);
	    }

	    @GetMapping("/orders/{orderId}/reviews")
	    public Iterable<Review> getReviews(@PathVariable int orderId) {
	        log.info("Get reviews for order {}", orderId);
	        return orderService.getReviews(orderId);
	    }

	    @DeleteMapping("/reviews/{reviewId}")
	    public void deleteReview(@PathVariable int reviewId) {
	        log.warn("Delete review {}", reviewId);
	        orderService.deleteReview(reviewId);
	    }
}