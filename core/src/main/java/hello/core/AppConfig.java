package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// AppConfig : 어플리케이션에 필요한 구형객체를 생성 / 생성자를 통해서 주입
public class AppConfig {
    // cmd + e : 히스토리 보기
    // [생성자주입]
    public MemberService memberService(){
        // DI(Dependency Injection) 의존관계주입
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
