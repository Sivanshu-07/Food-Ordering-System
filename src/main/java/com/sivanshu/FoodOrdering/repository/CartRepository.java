package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomerId(Long customer_id);
}
