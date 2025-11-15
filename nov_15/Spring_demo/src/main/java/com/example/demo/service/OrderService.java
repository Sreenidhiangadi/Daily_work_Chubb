package com.example.demo.service;
import com.example.demo.request.Order;
import com.example.demo.request.Review;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.*;
@Service
@Slf4j
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	public Iterable<Order> getAllOrders(){
		return orderRepo.findAll();
	}
public Order saveOrder(Order o) {
	return orderRepo.save(o);
}
public void deleteOrder(int id) {
	orderRepo.deleteById(id);
}

public Order addReview(int orderId, Review review) {
    Order order = orderRepo.findById(orderId)
                           .orElseThrow(() -> new RuntimeException("Order not found"));

    review.setOrder(order);      
    reviewRepo.save(review);       

    return order;
}
public Iterable<Review> getReviews(int orderId) {
    Order order = orderRepo.findById(orderId)
                           .orElseThrow(() -> new RuntimeException("Order not found"));
    return order.getReviews();
}
public void deleteReview(int reviewId) {
    reviewRepo.deleteById(reviewId);
}
}