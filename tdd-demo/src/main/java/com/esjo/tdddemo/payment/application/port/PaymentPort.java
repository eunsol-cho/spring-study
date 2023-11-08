package com.esjo.tdddemo.payment.application.port;


import com.esjo.tdddemo.order.domain.Orders;
import com.esjo.tdddemo.payment.domain.Payment;

public interface PaymentPort {
    Orders getOrder(Long orderId);

    void pay(int totalPrice, String cardNumber);

    void save(Payment payment);
}
