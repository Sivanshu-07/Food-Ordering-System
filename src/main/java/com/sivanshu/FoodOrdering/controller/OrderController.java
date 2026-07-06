package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import com.sivanshu.FoodOrdering.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customer/{customer_id}")
    public String placeOrder(@PathVariable Long customer_id){
        orderService.placeOrder(customer_id);
        return "Order Placed";
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.viewOrders(customerId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.viewOrder(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        return orderService.viewRestaurantOrders(restaurantId);
    }

    @PutMapping("/{orderId}/status/{status}")
    public String updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "Order status updated to " + status + " successfully.";
    }

    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable OrderStatus status) {
        return orderService.viewOrderByStatus(status);
    }
    @DeleteMapping("/{order_id}")
    public String deleteOrder(@PathVariable Long order_id){
        orderService.deleteOrder(order_id);
        return "Order with Id: "+order_id+ " Deleted SuccessFully !";
    }
}
