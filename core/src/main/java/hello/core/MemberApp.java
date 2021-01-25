package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm
    public static void main(String[] args) {
        // [1] POJO
        // ASIS
        //MemberService memberService = new MemberServiceImpl();
        // TOBE
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // [2] Spring framwork
        // *ApplicationContext : 스프링에서 객체(빈)들을 관리해주 컨테이너 = 스프링컨테이너
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // param = 이름(메서드명), 타입

        // cmd + opt + v : 변수 자동선언
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        // sout
        System.out.println("new member = " + member);
        // soutv
        System.out.println("findMember = " + findMember);
    }
}
