package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerId(Long customerId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByOrderStatus(OrderStatus status);
}
