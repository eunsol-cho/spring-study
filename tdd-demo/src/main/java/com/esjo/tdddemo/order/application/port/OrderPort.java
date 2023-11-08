package com.esjo.tdddemo.order.application.port;


import com.esjo.tdddemo.order.domain.Orders;
import com.esjo.tdddemo.product.domain.Product;

public interface OrderPort {
    Product getProductById(final Long productId);

    void save(final Orders order);
}
