package com.sivanshu.FoodOrdering.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToOne(mappedBy = "order")
    private Rating rating; // this will not create a separate column(rating) in order table , here rating is just mappedBy order that's it

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private double totalOrderPrice;

    private LocalDateTime orderDate; // LocalDateTime is Modern Date-Time API introduced in java 8
//    LocalDateTime.now(); -> this will give current date and time (2026-06-27T21:30:45)

    @JsonManagedReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) // mappedBy takes Object's variable name, Cascade propagates parent operations (save, update, delete, etc.) to child entities.
    private List<OrderItem> orderItems;

    @JsonManagedReference
    @OneToOne(mappedBy = "order")
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

