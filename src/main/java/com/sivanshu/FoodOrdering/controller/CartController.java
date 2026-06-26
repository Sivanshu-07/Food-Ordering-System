package com.sivanshu.FoodOrdering.controller;


import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}/add/{foodItemId}")
    public String addCart(@PathVariable Long customerId,@PathVariable Long foodItemId){
        cartService.addFoodToCart(customerId,foodItemId);
        return "Food item added to customer's cart successfully.";
    }
    @GetMapping("/{customerId}")
    public Cart getCartByCustomerId(@PathVariable Long customerId){
        return cartService.viewCart(customerId);
    }
    @DeleteMapping("/{customerId}/remove/{foodItemId}")
    public String removeFoodCart(@PathVariable Long customerId,@PathVariable Long foodItemId){
        cartService.removeFoodFromCart(customerId,foodItemId);
        return "FoodItem with foodItemId: "+foodItemId+" has been Deleted from the cart of customer_id: "+customerId;
    }
    @DeleteMapping("/{customerId}/clear")
    public String clearCart(@PathVariable Long customerId){
        cartService.clearCart(customerId);
        return "Cart of customer_id:"+customerId+" is empty now !";
    }
}
