package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.FoodItem;
import com.sivanshu.FoodOrdering.entity.Restaurant;
import com.sivanshu.FoodOrdering.enums.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    List<FoodItem> findByCategory(FoodCategory foodCategory); // Because in a category -> multiples fooditems will  be there
    List<FoodItem> findByRestaurant(Restaurant restaurant); // Because in a restaurant -> multiple fooditems will be there
    List<FoodItem> findByAvailable(Boolean available); // all the items can be present
    List<FoodItem> findByRestaurantAndAvailable(Restaurant rest,boolean available);
}
