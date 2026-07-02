package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Rating;

import java.util.List;

public interface RatingService {
    void addRating(Long orderId, double rating, String review);
    void updateRating(Long ratingId, double rating, String review);
    Rating getRatingByOrderId(Long orderId);
    List<Rating> getRatingsByCustomerId(Long customerId);
    List<Rating> getRatingsByRestaurantId(Long restaurantId);
    void deleteRating(Long ratingId);
}
