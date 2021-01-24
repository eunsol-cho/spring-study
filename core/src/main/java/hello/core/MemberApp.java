package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    //psvm
    public static void main(String[] args) {
        // ASIS
        //MemberService memberService = new MemberServiceImpl();
        // TOBE
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

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
