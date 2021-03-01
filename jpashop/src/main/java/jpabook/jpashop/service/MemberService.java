package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

@Service
// javax 가 아닌 스프링에서 지원해주는걸 쓰는게 보다 많은 옵션을 제공한다.
// JPA의 데이터 변경은 트렌젝션 안에서 이루어 져야한다. public 메서드는 트렌젝션 범위에 들어간다.
//@Transactional
@Transactional(readOnly = true) // 읽는곳에서는 해당 옵션을 주면, 보다 성능 최적화
//@AllArgsConstructor // 4. 멤버변수 모두를 포함한 생성자를 자동으로 만들어 준다.
@RequiredArgsConstructor // 5. final 멤버변수만 포함한 생성자를 자동으로 만들어 준다.
public class MemberService {

    // final로 선언해야 초기화 없을시, 컴파일 에러가 난다.
    private final MemberRepository memberRepository;

    // 1. 필드 인젝션
    /*@Autowired
    private MemberRepository memberRepository;*/

    // 2. setter 인젝션
    // 장점) 테스트시 mock객체를 주입하기에 좋다.
    // 단점) runtime에 누군가 바꿀수 있다. 왜냐면, 어플리케이션 로딩 시점 이후에 바꾸는 경우는 거의 없음.
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    // 3. 생성자 인젝션 *권장
    // 테스트 케이스 작성 용이. 변경 불가.
    //@Autowired // 어노테이션 생략을 해도, 생성자가 하나면 주입해줌.
    /*public MemberService(MemberRepository memberRepository){
       this.memberRepository = memberRepository;
    }*/


    /**
     * 회원 가입
     */
    @Transactional // readOnly = false 로 먹음.
    public Long join(Member member){
        vaildateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
