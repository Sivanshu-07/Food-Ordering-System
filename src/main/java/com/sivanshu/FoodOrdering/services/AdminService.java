package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.*;
import com.sivanshu.FoodOrdering.enums.OrderStatus;

import java.util.List;

public interface AdminService {
    // For Admin
    void createAdmin(Admin admin);

    Admin login(String email,String password); // because if the email and password is correct then we will get an admin object

    Admin getAdmin(Long adminId);

    // Methods that admit will perform
    List<Customer> getAllCustomers();

    List<Restaurant> getAllRestaurants();

    List<FoodItem> getAllFoodItems();

    List<Order> getAllOrders();

    List<Payment> getAllPayments();

    List<Rating> getAllRatings();

    void deleteCustomer(Long customerId);

    void deleteRestaurant(Long restaurantId);

    void deleteFoodItem(Long foodItemId);

    void deleteOrder(Long orderId);

    void deleteRating(Long ratingId);
}
