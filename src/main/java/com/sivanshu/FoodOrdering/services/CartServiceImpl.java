package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.entity.CartItem;
import com.sivanshu.FoodOrdering.entity.FoodItem;
import com.sivanshu.FoodOrdering.repository.CartItemRepository;
import com.sivanshu.FoodOrdering.repository.CartRepository;
import com.sivanshu.FoodOrdering.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartrepo;

    @Autowired
    private FoodItemRepository foodrepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Override
    public void addFoodToCart(Long customerId, Long foodItemId) {
        Cart cart = cartrepo.findByCustomerId(customerId);
        FoodItem foodItem = foodrepo.findById(foodItemId).orElseThrow();
        if(cart.getRestaurant()==null){
            cart.setRestaurant(foodItem.getRestaurant());
            cartrepo.save(cart);
        }
        else if(!Objects.equals(cart.getRestaurant().getId(), foodItem.getRestaurant().getId())){
            // Different restaurant is not allowed
            throw new RuntimeException("You can order from only one restaurant at a time.");
        }
        Optional<CartItem> existingcartItem = cartItemRepo.findByCartAndFoodItem(cart,foodItem);
        if(existingcartItem.isPresent()){
                CartItem item = existingcartItem.get();
                item.setQuantity(item.getQuantity()+1);
                cartItemRepo.save(item);
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setFoodItem(foodItem);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(1);
            cartItemRepo.save(newCartItem);
        }
    }

    @Override
    public Cart viewCart(Long customerId) {
        return cartrepo.findByCustomerId(customerId);
    }

    @Override
    public void removeFoodFromCart(Long customerId, Long foodItemId) {
        Cart cart = cartrepo.findByCustomerId(customerId);
        FoodItem foodItem = foodrepo.findById(foodItemId).orElseThrow();
        CartItem cartItem = cartItemRepo.findByCartAndFoodItem(cart,foodItem).orElseThrow();
        // 1. Delete from database
        cartItemRepo.delete(cartItem);

        // 2. Remove from in-memory collection
        cart.getCartItems().remove(cartItem);

        // after deleting cartitems , cartitems will become empty not null
        if(cart.getCartItems().isEmpty()){
            cart.setRestaurant(null);
            cartrepo.save(cart);
        }
    }

    @Override
    public void clearCart(Long customerId) {
        Cart cart = cartrepo.findByCustomerId(customerId);
        List<CartItem> cartItems = cart.getCartItems();
        cartItemRepo.deleteAll(cartItems);
        cart.getCartItems().clear();
        cart.setRestaurant(null);
        cartrepo.save(cart);
    }
}
