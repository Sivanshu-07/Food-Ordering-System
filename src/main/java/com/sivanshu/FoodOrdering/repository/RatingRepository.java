package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByOrderId(Long orderId);
    List<Rating> findByRestaurantId(Long restaurantId);
    List<Rating> findByCustomerId(Long customerId);
}
