package com.sivanshu.FoodOrdering.services;

import com.sivanshu.FoodOrdering.entity.Order;
import com.sivanshu.FoodOrdering.entity.Payment;
import com.sivanshu.FoodOrdering.enums.OrderStatus;
import com.sivanshu.FoodOrdering.enums.PaymentMethod;
import com.sivanshu.FoodOrdering.enums.PaymentStatus;
import com.sivanshu.FoodOrdering.repository.OrderRepository;
import com.sivanshu.FoodOrdering.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    @Transactional
    public void processPayment(Long orderId,
                               PaymentMethod paymentMethod,
                               boolean paymentSuccess) {

        // 1. Find Order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with Id: " + orderId));

        // 2. Check if payment already exists
        Payment payment = paymentRepository.findByOrderId(orderId);

        // 3. If payment already completed, don't allow another payment
        if (payment != null &&
                payment.getPaymentStatus() == PaymentStatus.SUCCESS) {

            throw new RuntimeException("Payment already completed.");
        }

        // 4. If payment doesn't exist, create it
        if (payment == null) {
            payment = new Payment();
            payment.setCustomer(order.getCustomer());
            payment.setOrder(order);
            payment.setAmount(order.getTotalOrderPrice());
        }
        // Updates the payment date for every new payment attempt,
        // including retries after a failed payment.
        payment.setPaymentDate(LocalDateTime.now());
        // 5. Update payment method
        payment.setPaymentMethod(paymentMethod);

        // 6. Process according to payment method
        if (paymentMethod == PaymentMethod.COD) {

            payment.setPaymentStatus(PaymentStatus.PENDING);
            order.setOrderStatus(OrderStatus.PENDING);

        } else { // UPI or CARD

            if (paymentSuccess) {

                payment.setPaymentStatus(PaymentStatus.SUCCESS);
                order.setOrderStatus(OrderStatus.PENDING);

            } else {

                payment.setPaymentStatus(PaymentStatus.FAILED);
                order.setOrderStatus(OrderStatus.PENDING_PAYMENT);

            }
        }

        // 7. Save
        orderRepository.save(order);
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public void updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new RuntimeException("No Payment Record found with Id: "+paymentId));
        if(payment.getPaymentStatus()==PaymentStatus.SUCCESS){
            throw new RuntimeException("Payment is already completed.");
        }
        payment.setPaymentStatus(paymentStatus);
        if(paymentStatus==PaymentStatus.SUCCESS && payment.getPaymentMethod()==PaymentMethod.COD){
            Order order = payment.getOrder();
            order.setOrderStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
        }
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus) {
        return paymentRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<Payment> getPaymentsByMethod(PaymentMethod paymentMethod) {
        return paymentRepository.findByPaymentMethod(paymentMethod);
    }
}
