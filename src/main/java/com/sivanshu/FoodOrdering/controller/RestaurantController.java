package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.Restaurant;
import com.sivanshu.FoodOrdering.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService resservice;

    @PostMapping
    public String registerRestaurant(@RequestBody Restaurant rest){
        resservice.registerRestaurant(rest);
        return "Restaurant Added Successfully !";
    }
    @GetMapping
    public List<Restaurant> getAllRestaurant(){
        return resservice.viewAllRestaurants();
    }
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id){
        return resservice.getRestaurant(id);
    }
    @PutMapping("/{id}")
    public String updateRestaurant(@PathVariable Long id, @RequestBody Restaurant rest){
        resservice.updateRestaurant(id,rest);
        return "Updated !";
    }
    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id){
        resservice.deleteRestaurant(id);
        return "Restaurant with Id: "+id+" Deleted SuccessFully !";
    }
}
