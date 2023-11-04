package com.esjo.tdddemo.product;

import org.springframework.stereotype.Component;

@Component
class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

    ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
