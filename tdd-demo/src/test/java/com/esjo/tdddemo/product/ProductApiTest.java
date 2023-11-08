package com.esjo.tdddemo.product;

import com.esjo.tdddemo.ApiTest;
import com.esjo.tdddemo.product.adapter.ProductRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.esjo.tdddemo.product.ProductSteps.상품등록요청;
import static com.esjo.tdddemo.product.ProductSteps.상품등록요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

class ProductApiTest extends ApiTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void 상품등록() {

        final var request = 상품등록요청_생성();

        final var response = 상품등록요청(request); // API 테스트

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }


    @Test
    void 상품조회() {
        상품등록요청(ProductSteps.상품등록요청_생성());
        Long productId = 1L;

        final var response = ProductSteps.상품조회요청(productId);

        AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        AssertionsForClassTypes.assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

    @Test
    void 상품수정() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        final long productId = 1L;

        final var response = ProductSteps.상품수정요청(productId);

        AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        AssertionsForClassTypes.assertThat(productRepository.findById(1L).get().getName()).isEqualTo("상품 수정");
    }

}
