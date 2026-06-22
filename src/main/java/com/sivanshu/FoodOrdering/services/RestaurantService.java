package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    // only declare methods
    void registerRestaurant(Restaurant restaurant);
    List<Restaurant> viewAllRestaurants();
    Restaurant getRestaurant(Long id);
    void updateRestaurant(Long id,Restaurant restaurant);
    void deleteRestaurant(Long id);
}
