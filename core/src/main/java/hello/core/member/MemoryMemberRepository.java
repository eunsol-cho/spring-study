package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// opt + enter : 자동완성
// 인터페이스와 구현체는 다른 패키지에 넣는게 좋다.
@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 이슈로 ConcurrentHashMap을 쓰는것이 좋다.
    
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
