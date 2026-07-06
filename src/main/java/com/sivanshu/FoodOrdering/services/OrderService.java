package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    // Customer
    void placeOrder(Long customerId);

    List<Order> viewOrders(Long customerId);

    Order viewOrder(Long orderId);

    // Restaurant
    List<Order> viewRestaurantOrders(Long restaurantId);

    void updateOrderStatus(Long orderId, OrderStatus status);

    // Admin
    List<Order> viewOrderByStatus(OrderStatus status);

    void deleteOrder(Long order_id);
}
