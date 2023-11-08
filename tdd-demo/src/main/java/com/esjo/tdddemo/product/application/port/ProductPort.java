package com.esjo.tdddemo.product.application.port;

import com.esjo.tdddemo.product.domain.Product;

public interface ProductPort {
    void save(final Product product);

    Product getProduct(Long productId);
}
