package hello.core.member;

// 구현체가 하나인 경로 관례로 ~Impl로 작성
public class MemberServiceImpl implements MemberService {

    // cmd + shift + enter : ;까지 자동완성됨
    // ASIS
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // TOBE -> 생성자를 통해서 MemberRepository를 현 [생성자주]
    // 구현체를 의존 하지 않는다.
    // 어떤 객체가 주입될지 고민 하지 않고, 실행에만 집중한다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
