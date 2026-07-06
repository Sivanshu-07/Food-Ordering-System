package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Restaurant;
import com.sivanshu.FoodOrdering.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository resrepo;

    @Override
    public void registerRestaurant(Restaurant restaurant) {
        if(resrepo.existsByNameAndAddress(restaurant.getName(),restaurant.getAddress())){
            throw new RuntimeException("Restaurant Already Exists !");
        }
        resrepo.save(restaurant);
    }

    @Override
    public List<Restaurant> viewAllRestaurants() {
        return resrepo.findAll();
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return resrepo.findById(id).orElseThrow();
    }

    @Override
    public void updateRestaurant(Long id, Restaurant restaurant) {
        Restaurant rest = resrepo.findById(id).orElseThrow();
        rest.setName(restaurant.getName());
        rest.setAddress(restaurant.getAddress());
        rest.setPhone(restaurant.getPhone());
        rest.setRating(restaurant.getRating());
        resrepo.save(rest);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = resrepo.findById(restaurantId)
                .orElseThrow(() ->
                        new RuntimeException("Restaurant not found with id: " + restaurantId));
        resrepo.delete(restaurant);
    }
}
