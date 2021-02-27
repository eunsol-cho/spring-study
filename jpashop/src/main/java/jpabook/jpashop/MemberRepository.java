package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    // cmd + shift + t : 테스트코드 만들기

    @PersistenceContext // 스프링부트가 EntityManager를 사용할수 있게 지원.
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리를 분리해라 : 쿼리 - 객체를 조회, 커맨드 - 객체의상태변경
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
