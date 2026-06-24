package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.FoodItem;
import com.sivanshu.FoodOrdering.enums.FoodCategory;
import com.sivanshu.FoodOrdering.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/restaurant/{restaurantId}")
    public String addFoodItem(@RequestBody FoodItem foodItem,@PathVariable Long restaurantId){
        foodItemService.addFoodItem(foodItem,restaurantId);
        return "FoodItems Added in the Respective Restaurant with restaurant_id: "+restaurantId;
    }
    @GetMapping
    public List<FoodItem> viewAllFoodItems(){
        return foodItemService.getAllFoodItems();
    }
    @GetMapping("/{id}")
    public FoodItem getFoodItemByid(@PathVariable Long id){
        return foodItemService.getFoodItemById(id);
    }
    @PutMapping("/{id}")
    public String updateFoodItem(@PathVariable Long id,@RequestBody FoodItem foodItem){
        foodItemService.updateFoodItem(id,foodItem);
        return "FoodItems Details Updated !";
    }
    @DeleteMapping("/{id}")
    public String deleteFoodItemByid(@PathVariable Long id){
        foodItemService.deleteFoodItem(id);
        return "FoodItem with id: "+id+" Deleted Successfully !";
    }
    @GetMapping("/category/{category}")
    public List<FoodItem> getFoodItemByCategory(@PathVariable FoodCategory category){
        return foodItemService.getFoodItemsByCategory(category);
    }
    @GetMapping("/available/{available}")
    public List<FoodItem> getFoodItemsByAvailability(
            @PathVariable boolean available) {
        return foodItemService.getAvailableFoodItems(available);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodItem> getFoodItemsByRestaurant(
            @PathVariable Long restaurantId) {

        return foodItemService
                .getFoodItemsByRestaurant(restaurantId);
    }
    @GetMapping("/restaurant/{restaurantId}/available/{available}")
    public List<FoodItem> getFoodItemsByRestaurantAndAvailability(
            @PathVariable Long restaurantId,
            @PathVariable boolean available) {

        return foodItemService
                .getFoodItemsByRestaurantAndAvailability(
                        restaurantId,
                        available
                );
    }
}
