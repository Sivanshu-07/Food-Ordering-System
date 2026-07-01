package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Payment;
import com.sivanshu.FoodOrdering.enums.PaymentMethod;
import com.sivanshu.FoodOrdering.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {

    // Process payment (UPI / CARD / COD)
    void processPayment(Long orderId,
                        PaymentMethod paymentMethod,
                        boolean paymentSuccess);

    // Customer payment history
    List<Payment> getPaymentsByCustomerId(Long customerId);

    // Get payment details of a particular order
    Payment getPaymentByOrderId(Long orderId);

    // Update payment status (Admin/Restaurant)
    void updatePaymentStatus(Long paymentId,
                             PaymentStatus paymentStatus);

    // View payments by status (Admin)
    List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus);

}