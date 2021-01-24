package hello.core.member;

// 구현체가 하나인 경로 관례로 ~Impl로 작성
public class MemberServiceImpl implements MemberService {

    // cmd + shift + enter : ;까지 자동완성됨
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
