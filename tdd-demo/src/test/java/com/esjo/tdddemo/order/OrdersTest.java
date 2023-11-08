package com.esjo.tdddemo.order;

import com.esjo.tdddemo.order.domain.Orders;
import com.esjo.tdddemo.product.domain.DiscountPolicy;
import com.esjo.tdddemo.product.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrdersTest {

    @Test
    void getTotalPrice() {
        final Orders order = new Orders(new Product("상품명", 2000, DiscountPolicy.FIX_1000_AMOUNT), 2);

        final int totalPrice = order.getTotalPrice();

        assertThat(totalPrice).isEqualTo(2000);
    }
}