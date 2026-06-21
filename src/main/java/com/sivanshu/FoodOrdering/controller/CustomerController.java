package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.Customer;
import com.sivanshu.FoodOrdering.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService cservice;

    @PostMapping
    public String createCustomer(@RequestBody Customer customer){
        cservice.registerCustomer(customer);
        return "Customer Registered Successfully !";
    }
    @GetMapping
    public List<Customer> getAllCustomer(){
        return cservice.viewAllCustomer();
    }
    @GetMapping("id/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        return cservice.getCustomerById(id);
    }
    @PutMapping("id/{id}")
    public String UpdateCusomerById(@PathVariable Long id,@RequestBody Customer customer){
        cservice.modifyCustomer(id,customer);
        return "Updated";
    }
    @DeleteMapping("id/{id}")
    public String deleteCustomerById(@PathVariable Long id){
        cservice.deleteCustomer(id);
        return "Customer with id: "+id+" Deleted Successfully !";
    }

}
