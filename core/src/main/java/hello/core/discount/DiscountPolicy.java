package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    // F2 : 오류난곳으로 이동

    /**
     * @return 할인 대상 금
     */
    int discount(Member member, int price);

}
