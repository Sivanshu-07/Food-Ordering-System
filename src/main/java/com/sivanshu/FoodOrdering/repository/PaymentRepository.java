package com.sivanshu.FoodOrdering.repository;

import com.sivanshu.FoodOrdering.entity.Payment;
import com.sivanshu.FoodOrdering.enums.PaymentMethod;
import com.sivanshu.FoodOrdering.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByCustomerId(Long customerId);

    Payment findByOrderId(Long orderId);

    List<Payment> findByPaymentStatus(PaymentStatus status);

    List<Payment> findByPaymentMethod(PaymentMethod paymentMethod);
}
