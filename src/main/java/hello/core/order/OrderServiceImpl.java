package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // (롬복 라이브러리 이다.) final이 붙은 필드를 모아서생성자를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정할인
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 퍼센트할인
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 문제점 : OCP에 어긋난다. 위 의존관계를 보면 구현 클래스에 의존하고 있다.
    // 이렇게 되면 할인정책이 바뀔때마다 Service 구현 클래스를 매번 변경해야 한다.

    // 해결 : 애플리케이션의 전체 동작 방식을 구성하기 위해 구현 객체를 생성하고, 연결하는
    // 책임을 가지는 별도의 설정 클래스를 만들어야 한다. (AppConfig)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // RequiredArgsConstructor 어노테이션이 생성자를 자동으로 만들어 준다.
    /*@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { // @MainDiscountPolicy는 직접 만든 어노테이션이다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 아이디로 회원의 정보를 조회
        Member member = memberRepository.findById(memberId);
        // 회원의 등급에 맞는 할인정책(금액)을 반환
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 주문 리턴
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
