package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Customer;
import com.sivanshu.FoodOrdering.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void registerCustomer(Customer customer);
    List<Customer> viewAllCustomer();
    Optional<Customer> getCustomerById(Long id);
    void modifyCustomer(Long id,Customer customer);
    void deleteCustomer(Long id);
}
