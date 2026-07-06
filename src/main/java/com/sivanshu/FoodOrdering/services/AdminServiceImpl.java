package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.*;
import com.sivanshu.FoodOrdering.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private RestaurantRepository restRepo;

    @Autowired
    private FoodItemRepository foodRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RatingRepository ratingRepo;

    @Override
    public void createAdmin(Admin admin) {
        if(adminRepo.existsByEmail(admin.getEmail())){
            throw new RuntimeException("Admin Already Exist !");
        }
        adminRepo.save(admin);
    }

    @Override
    public Admin login(String email, String password) {
        Admin existingAdmin = adminRepo.findByEmail(email);
        if(existingAdmin == null){
            throw new RuntimeException("Admin not found!");
        }
        if(!existingAdmin.getPassword().equals(password)){
            throw new RuntimeException("Incorrect Password!");
        }
        return existingAdmin;
    }

    @Override
    public Admin getAdmin(Long adminId) {
        return adminRepo.findById(adminId).orElseThrow(()-> new RuntimeException("Admin Doesn't Exist !"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return custRepo.findAll();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restRepo.findAll();
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodRepo.findAll();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = custRepo.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found with Id: "+customerId));
        custRepo.delete(customer);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restRepo.findById(restaurantId).orElseThrow(()-> new RuntimeException("Restaurant not found with Id: "+restaurantId));
        restRepo.delete(restaurant);
    }

    @Override
    public void deleteFoodItem(Long foodItemId) {
        FoodItem foodItem = foodRepo.findById(foodItemId).orElseThrow(()->new RuntimeException("FoodItem not found with Id: "+foodItemId));
        foodRepo.delete(foodItem);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found with Id: "+orderId));
        orderRepo.delete(order);
    }

    @Override
    @Transactional
    public void deleteRating(Long ratingId) {
        Rating rating = ratingRepo.findById(ratingId).orElseThrow(()->new RuntimeException("Rating not found with Id: "+ratingId));
        Order order = rating.getOrder();
        if (order != null) {
            order.setRating(null);
            orderRepo.save(order);
        }
        ratingRepo.delete(rating);
    }
}
