package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        // ASIS
        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();
        // TOBE
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        // control + r : 기존에 마지막 실행됫던걸로 실행
        System.out.println("order = " + order);
    }
}
