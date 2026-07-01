package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.entity.CartItem;
import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.entity.OrderItem;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import com.sivanshu.FoodOrdering.repository.CartRepository;
import com.sivanshu.FoodOrdering.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional // this is for -> suppose everything goes correct but method throws a exception at the last line while clearing the cart then everything which we have done earlier,it should be roll back
    public void placeOrder(Long customerId) {
        Cart cart = cartRepo.findByCustomerId(customerId);
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()){
            throw new RuntimeException("Cart is empty !");
        }
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setRestaurant(cart.getRestaurant());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice=0;
        for(CartItem item : cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setFoodItem(item.getFoodItem());
            orderItem.setQuantity(item.getQuantity());
            totalPrice += (item.getFoodItem().getPrice()* item.getQuantity());
            orderItem.setPriceAtOrder(item.getFoodItem().getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setTotalOrderPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepo.save(order);
        cartService.clearCart(customerId);
    }

    @Override
    public List<Order> viewOrders(Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    @Override
    public Order viewOrder(Long orderId) {
        return orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found with orderId: "+orderId));
    }

    @Override
    public List<Order> viewRestaurantOrders(Long restaurantId) {
        return orderRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found with orderId: "+orderId));
        order.setOrderStatus(status);
        orderRepo.save(order);
    }

    @Override
    public List<Order> viewOrderByStatus(OrderStatus status) {
        return orderRepo.findByOrderStatus(status);
    }
}
