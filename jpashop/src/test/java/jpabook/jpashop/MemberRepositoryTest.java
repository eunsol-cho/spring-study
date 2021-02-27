package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    // 1. EntityManager를 통한 모든 데이터 변경은 트렌젝션 안에서 이루어져야한다!
    // 2. Transactional 이 test에 있으면, 데이터를 모두 롤백한다.
    @Rollback(false) // Transactional이 있어도, 데이터를 롤백하지 않는다.
    public void testMember() throws Exception{
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 같은 영속성 컨텍스트로 true 리턴 : 실제로 select 하지 않고 1차 캐시에서 들고온다
        System.out.println("(findMember==member) = " + (findMember==member));
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}