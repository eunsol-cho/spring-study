package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // FK를 적어준다 -> 연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // OneToOne관계에서는 연관관계의 주인을 정해줘야함. -> 보편적으로 order에서 delivery를 봄.

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // enum일때 반드시 해당 조건을 줘야함. ORDINAL이 default인데 순서값을 가짐. 추가되면 망함.
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
