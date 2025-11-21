package com.example.demo.service;

import com.example.demo.request.Order;
import com.example.demo.request.Product;
import com.example.demo.request.Review;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductRepository productRepo;

    public Iterable<Order> getAllOrders() {
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

    public Order addProductToOrder(int orderId, int productId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        order.getProducts().add(product);
        return orderRepo.save(order);
    }

    public Order removeProductFromOrder(int orderId, int productId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        order.getProducts().remove(product);
        return orderRepo.save(order);
    }

    public List<Product> getProductsOfOrder(int orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getProducts();
    }
}
