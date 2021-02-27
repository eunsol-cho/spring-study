package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // enum일때 반드시 해당 조건을 줘야함. ORDINAL이 default인데 순서값을 가짐. 추가되면 망함.
    private DeliveryStatus deliveryStatus; // READY, COMP

}
