package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.FoodItem;
import com.sivanshu.FoodOrdering.entity.Restaurant;
import com.sivanshu.FoodOrdering.enums.FoodCategory;
import com.sivanshu.FoodOrdering.repository.FoodItemRepository;
import com.sivanshu.FoodOrdering.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService{

    @Autowired
    private FoodItemRepository foodrepo;

    @Autowired
    private RestaurantRepository restrepo;

    @Override
    public void addFoodItem(FoodItem fooditems, Long restaurantId) {
        Restaurant rest = restrepo.findById(restaurantId).orElseThrow();
        fooditems.setRestaurant(rest);
        foodrepo.save(fooditems);
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodrepo.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodrepo.findById(id).orElseThrow();
    }

    @Override
    public void updateFoodItem(Long id, FoodItem foodItem) { // here id -> FoodItem id
        FoodItem existingFood = foodrepo.findById(id).orElseThrow();
        existingFood.setName(foodItem.getName());
        existingFood.setPrice(foodItem.getPrice());
        existingFood.setCategory(foodItem.getCategory());
        existingFood.setAvailable(foodItem.isAvailable());
        foodrepo.save(existingFood);
    }

    @Override
    public void deleteFoodItem(Long foodItemId) {
        FoodItem foodItem = foodrepo.findById(foodItemId)
                .orElseThrow(() ->
                        new RuntimeException("FoodItem not found with id: " + foodItemId));
        foodrepo.delete(foodItem);
    }

    @Override
    public List<FoodItem> getFoodItemsByCategory(FoodCategory category) {
        return foodrepo.findByCategory(category);
    }

    @Override
    public List<FoodItem> getAvailableFoodItems(Boolean available) {
        return foodrepo.findByAvailable(available);
    }

    @Override
    public List<FoodItem> getFoodItemsByRestaurant(Long restaurantId) {
        Restaurant restaurant = restrepo.findById(restaurantId).orElseThrow();
        return foodrepo.findByRestaurant(restaurant);
    }
    @Override
    public List<FoodItem> getFoodItemsByRestaurantAndAvailability(
            Long restaurantId,
            boolean available) {

        Restaurant restaurant = restrepo
                .findById(restaurantId)
                .orElseThrow();

        return foodrepo.findByRestaurantAndAvailable(
                restaurant,
                available
        );
    }

}
