package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.entity.Rating;
import com.sivanshu.FoodOrdering.entity.Restaurant;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import com.sivanshu.FoodOrdering.repository.OrderRepository;
import com.sivanshu.FoodOrdering.repository.RatingRepository;
import com.sivanshu.FoodOrdering.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Override
    @Transactional
    public void addRating(Long orderId, double ratingValue, String review) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        if (ratingValue < 1 || ratingValue > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        if (order.getOrderStatus() != OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot rate an order that is not delivered! Current status: " + order.getOrderStatus());
        }

        if (ratingRepo.findByOrderId(orderId).isPresent()) {
            throw new RuntimeException("This order has already been rated!");
        }

        Rating rating = new Rating();
        rating.setOrder(order);
        rating.setCustomer(order.getCustomer());
        rating.setRestaurant(order.getRestaurant());
        rating.setRating(ratingValue);
        rating.setReview(review);
        rating.setRatingDate(LocalDateTime.now());

        ratingRepo.save(rating);

        order.setRating(rating);
        orderRepo.save(order);

        // Recalculate and update restaurant average rating
        calculateAndupdateRestaurantAverageRating(order.getRestaurant().getId());
    }

    @Override
    @Transactional
    public void updateRating(Long ratingId, double ratingValue, String review) {
        Rating existingRating = ratingRepo.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + ratingId));

        if (ratingValue < 1 || ratingValue > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        existingRating.setRating(ratingValue);
        existingRating.setReview(review);
        existingRating.setRatingDate(LocalDateTime.now());

        ratingRepo.save(existingRating);

        // Recalculate and update restaurant average rating
        calculateAndupdateRestaurantAverageRating(existingRating.getRestaurant().getId());
    }

    @Override
    public Rating getRatingByOrderId(Long orderId) {
        return ratingRepo.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Rating not found for order id: " + orderId));
    }

    @Override
    public List<Rating> getRatingsByRestaurantId(Long restaurantId) {
        return ratingRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Rating> getRatingsByCustomerId(Long customerId) {
        return ratingRepo.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void deleteRating(Long ratingId) {
        Rating existingRating = ratingRepo.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + ratingId));

        Order order = existingRating.getOrder();
        if (order != null) {
            order.setRating(null);
            orderRepo.save(order);
        }

        Long restaurantId = existingRating.getRestaurant().getId();
        ratingRepo.delete(existingRating);

        // Recalculate and update restaurant average rating
        calculateAndupdateRestaurantAverageRating(restaurantId);
    }

    private void calculateAndupdateRestaurantAverageRating(Long restaurantId) {
        List<Rating> ratings = ratingRepo.findByRestaurantId(restaurantId);
        double averageRating = 0.0;
        if (!ratings.isEmpty()) {
            double sum = 0.0;
            for (Rating r : ratings) {
                sum += r.getRating();
            }
            averageRating = sum / ratings.size();
        }

        // Round to 1 decimal place
        averageRating = Math.round(averageRating * 10.0) / 10.0;

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
        restaurant.setRating(averageRating);
        restaurantRepo.save(restaurant);
    }
}
