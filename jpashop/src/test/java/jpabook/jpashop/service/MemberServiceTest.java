package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

// junit을 스프링과 같이 실행하기 위해
@RunWith(SpringRunner.class)
// 스프링 부트를 띄운 상태로 테스트 하기 위해. 없으면, Autowired 에러남
@SpringBootTest
// 테스트 끝나면 모두 Rollback
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        //em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim2");

        // when
        memberService.join(member1);
        //try {
            memberService.join(member2); // 예외가 발생한다!
        //} catch (IllegalStateException e){
            //return;
        //}

        // then
        fail("예외가 발생해야 한다."); // 코드가 돌다가 여길 오면 안된다.
    }
}