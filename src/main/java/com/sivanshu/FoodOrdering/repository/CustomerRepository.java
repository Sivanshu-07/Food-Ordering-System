package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email); // spring itself will generate the implementation of this method
}
