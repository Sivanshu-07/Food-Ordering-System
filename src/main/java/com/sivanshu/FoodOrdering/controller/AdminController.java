package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.*;
import com.sivanshu.FoodOrdering.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public String createAdmin(@RequestBody Admin admin) {
        adminService.createAdmin(admin);
        return "Admin Registered Successfully !";
    }

    @PostMapping("/login/email/{email}/password/{password}")
    public Admin login(@PathVariable String email, @PathVariable String password) {
        return adminService.login(email, password);
    }

    @GetMapping("/{adminId}")
    public Admin getAdmin(@PathVariable Long adminId) {
        return adminService.getAdmin(adminId);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return adminService.getAllRestaurants();
    }

    @GetMapping("/fooditems")
    public List<FoodItem> getAllFoodItems() {
        return adminService.getAllFoodItems();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return adminService.getAllPayments();
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        return adminService.getAllRatings();
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        adminService.deleteCustomer(customerId);
        return "Customer Deleted Successfully !";
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(@PathVariable Long restaurantId) {
        adminService.deleteRestaurant(restaurantId);
        return "Restaurant Deleted Successfully !";
    }

    @DeleteMapping("/fooditem/{foodItemId}")
    public String deleteFoodItem(@PathVariable Long foodItemId) {
        adminService.deleteFoodItem(foodItemId);
        return "FoodItem Deleted Successfully !";
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        adminService.deleteOrder(orderId);
        return "Order Deleted Successfully !";
    }

    @DeleteMapping("/rating/{ratingId}")
    public String deleteRating(@PathVariable Long ratingId) {
        adminService.deleteRating(ratingId);
        return "Rating Deleted Successfully !";
    }
}
