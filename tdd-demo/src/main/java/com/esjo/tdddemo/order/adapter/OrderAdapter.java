package com.esjo.tdddemo.order.adapter;

import com.esjo.tdddemo.order.application.port.OrderPort;
import com.esjo.tdddemo.order.domain.Orders;
import com.esjo.tdddemo.product.adapter.ProductRepository;
import com.esjo.tdddemo.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
class OrderAdapter implements OrderPort {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private OrderAdapter(final ProductRepository productRepository, final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Product getProductById(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Override
    public void save(final Orders order) {
        orderRepository.save(order);
    }
}
