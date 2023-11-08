package com.esjo.tdddemo.payment.adapter;


import com.esjo.tdddemo.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

interface PaymentRepository extends JpaRepository<Payment, Long> {
}
