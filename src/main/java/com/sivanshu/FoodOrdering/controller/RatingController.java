package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.Rating;
import com.sivanshu.FoodOrdering.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/order/{orderId}/rating/{rating}/review/{review}")
    public String addRating(
            @PathVariable Long orderId,
            @PathVariable double rating,
            @PathVariable String review) {
        ratingService.addRating(orderId, rating, review);
        return "Rating Added Successfully!";
    }

    @PutMapping("/{ratingId}/rating/{rating}/review/{review}")
    public String updateRating(
            @PathVariable Long ratingId,
            @PathVariable double rating,
            @PathVariable String review) {
        ratingService.updateRating(ratingId, rating, review);
        return "Rating Updated Successfully!";
    }

    @GetMapping("/order/{orderId}")
    public Rating getRatingByOrderId(@PathVariable Long orderId) {
        return ratingService.getRatingByOrderId(orderId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Rating> getRatingsByRestaurantId(@PathVariable Long restaurantId) {
        return ratingService.getRatingsByRestaurantId(restaurantId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Rating> getRatingsByCustomerId(@PathVariable Long customerId) {
        return ratingService.getRatingsByCustomerId(customerId);
    }

    @DeleteMapping("/{ratingId}")
    public String deleteRating(@PathVariable Long ratingId) {
        ratingService.deleteRating(ratingId);
        return "Rating Deleted Successfully!";
    }
}
