package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.FoodItem;
import com.sivanshu.FoodOrdering.enums.FoodCategory;

import java.util.List;

public interface FoodItemService {
    void addFoodItem(FoodItem fooditems,Long restaurantId);
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(Long id);
    void updateFoodItem(Long id, FoodItem foodItem);
    void deleteFoodItem(Long id);
    List<FoodItem> getFoodItemsByCategory(FoodCategory category);
    List<FoodItem> getAvailableFoodItems(Boolean available);
    List<FoodItem> getFoodItemsByRestaurant(Long restaurantId); // client will not provide full restaurant details, client will only tell the restaurantId and serviceImpl will have to fetch the Restaurant by restaurantId
    List<FoodItem> getFoodItemsByRestaurantAndAvailability(
            Long restaurantId,
            boolean available
    );
}
