package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Cart;
import com.sivanshu.FoodOrdering.entity.Customer;
import com.sivanshu.FoodOrdering.repository.CartRepository;
import com.sivanshu.FoodOrdering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired // dependency injection means give me the object of CustomerRepository
    private CustomerRepository crepo; // Now I can use the methods of Repository here

    @Autowired
    private CartRepository cartRepo;

    @Override
    public void registerCustomer(Customer customer) {
        if(crepo.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Customer Already Exists !"); // method will end here
        }
        Customer savedCustomer = crepo.save(customer);
        Cart cart = new Cart();
        cart.setCustomer(savedCustomer);
        cartRepo.save(cart);
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

    @Override
    public Customer login(String email, String password) {
        Customer existingCustomer = crepo.findByEmail(email).orElseThrow(()->new RuntimeException("Customer Not Found"));
        if (!existingCustomer.getPassword().equals(password)) {
            throw new RuntimeException("Incorrect Password!");
        }
        return existingCustomer;
    }
}
