package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Customer;
import com.sivanshu.FoodOrdering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired // dependency injection means give me the object of CustomerRepository
    private CustomerRepository crepo; // Now I can use the methods of Repository here

    @Override
    public void registerCustomer(Customer customer) {
        if(crepo.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email Already Exists !"); // method will end here
        }
        crepo.save(customer);
    }

    @Override
    public List<Customer> viewAllCustomer() {
        return crepo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return crepo.findById(id);
    }

    @Override
    public void modifyCustomer(Long id, Customer customer) {
        Customer cust = crepo.findById(id).orElseThrow();
        cust.setName(customer.getName());
        cust.setEmail(customer.getEmail());
        cust.setPassword(customer.getPassword());
        crepo.save(cust);
    }

    @Override
    public void deleteCustomer(Long id) {
        crepo.deleteById(id);
    }
}
