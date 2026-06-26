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
        cartItemRepo.delete(cartItem);
    }

    @Override
    public void clearCart(Long customerId) {
        Cart cart = cartrepo.findByCustomerId(customerId);
        // We are not deleting the cart as it is permanent when the customer register
        // we are deleting cartitems from the respective cart bcuz if the carditem gets deleted->Cart will automatically become empty
        List<CartItem> cartItems = cart.getCartItems();
        cartItemRepo.deleteAll(cartItems); // whenever we need to delete the whole list we will use deleteAll() provided by JpaRepo
    }
}
