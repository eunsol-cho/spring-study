package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig : 어플리케이션에 필요한 구형객체를 생성 / 생성자를 통해서 주입
@Configuration
public class AppConfig {
    // cmd + e : 히스토리 보기
    // cmd + opt + m : 메서드로 추
    // [생성자주입]
    @Bean
    public MemberService memberService(){
        // DI(Dependency Injection) 의존관계주입
        // ASIS
        //return new MemberServiceImpl(new MemoryMemberRepository());
        // TOBE : 역할 구현을 분리
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        // ASIS
        //return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        // TOBE : 역할 구현을 분리
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 할인정책 변경시 해당 소스만 변경하면 된다.
        return new FixDiscountPolicy();
        //return new RateDiscountPolicy();
    }

}
