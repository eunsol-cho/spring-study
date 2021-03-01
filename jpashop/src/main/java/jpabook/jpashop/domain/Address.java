package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // protected로 기본 생성자 선언. JPA스팩. 사실 상속할일 없음.
    protected Address() {
    }

    // 값타입은 변경 불가능 하게 설계. setter 제공하지 않음.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
