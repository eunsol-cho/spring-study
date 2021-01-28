package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // ASIS
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 할인정책 변경 -> 구현체 변경
    // 그런데, 할인정책을 변경하는데, 클라이언트 (OrderServiceImpl)를 수정해야한다는 문제가 발생. : OCP위반
    // 클라이언트에서 인터페이스만 의존하지 않고, 구현체까지 의존 : DIP위반
    //private DiscountPolicy discountPolicy; // NullpointException발생
    // 수정방향
    // => 클라이언트에 (OrderServiceImpl) DiscountPolicy의 구현체를 대신 생성해서 주입해주어야한다.
    // => 인터페이스에만 의존하게 해야한다.

    // AppConfig를 OCP/DIP문제를 해결
    // TOBE
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
