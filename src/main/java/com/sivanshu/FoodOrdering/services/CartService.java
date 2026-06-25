package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Cart;

public interface CartService {
    void addFoodToCart(Long customerId, Long foodItemId);

    Cart viewCart(Long customerId);

    void removeFoodFromCart(Long customerId, Long foodItemId);

    void clearCart(Long customerId);
}
