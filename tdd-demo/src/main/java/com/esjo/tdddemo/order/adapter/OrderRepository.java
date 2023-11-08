package com.esjo.tdddemo.order.adapter;


import com.esjo.tdddemo.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
