package com.esjo.tdddemo.payment.domain;

import com.esjo.tdddemo.order.domain.Orders;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Orders order;
    private String cardNumber;

    public Payment(final Orders order, final String cardNumber) {
        Assert.notNull(order, "주문은 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
        this.order = order;
        this.cardNumber = cardNumber;
    }

    public int getPrice() {
        return order.getTotalPrice();
    }
}
