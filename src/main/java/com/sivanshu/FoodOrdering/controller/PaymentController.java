package com.sivanshu.FoodOrdering.controller;

import com.sivanshu.FoodOrdering.entity.Payment;
import com.sivanshu.FoodOrdering.enums.PaymentMethod;
import com.sivanshu.FoodOrdering.enums.PaymentStatus;
import com.sivanshu.FoodOrdering.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process/order/{orderId}/method/{paymentMethod}/success/{paymentSuccess}")
    public String processPayment(
            @PathVariable Long orderId,
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable boolean paymentSuccess) {
        paymentService.processPayment(orderId, paymentMethod, paymentSuccess);
        return "Payment Processed";
    }

    @GetMapping("/customer/{customerId}")
    public List<Payment> getPaymentsByCustomerId(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomerId(customerId);
    }

    @GetMapping("/order/{orderId}")
    public Payment getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }

    @PutMapping("/{paymentId}/status/{paymentStatus}")
    public String updatePaymentStatus(
            @PathVariable Long paymentId,
            @PathVariable PaymentStatus paymentStatus) {
        paymentService.updatePaymentStatus(paymentId, paymentStatus);
        return "Payment status updated successfully.";
    }

    @GetMapping("/status/{paymentStatus}")
    public List<Payment> getPaymentsByStatus(@PathVariable PaymentStatus paymentStatus) {
        return paymentService.getPaymentsByStatus(paymentStatus);
    }
}
