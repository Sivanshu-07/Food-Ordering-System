package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.entity.CartItem;
import com.sivanshu.FoodOrdering.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndFoodItem(Cart cart, FoodItem foodItem);
}
