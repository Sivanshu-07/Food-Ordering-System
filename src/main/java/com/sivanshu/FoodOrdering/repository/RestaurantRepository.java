package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    boolean existsByNameAndAddress(String name,String address); // spring itself will generate the implementation of this method
}
