package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService -> getMemberRepository() -> new MemoryMemberRepository()
    // @Bean orderService -> getMemberRepository() -> new MemoryMemberRepository()
    // 2번 호출 되어 참조값이 다를까? 그렇지 않다. 한번만 호출된다. @Configuration 을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        // 생성자를 통해 주입한다.
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 중복 제거와 역할-구현 구체화
    // 메소드 추출 단축키 Ctrl + Alt + M

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.getMemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    // OCP : 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
    //      애플리케이션을 사용 영역과 구성 영역으로 나눔
    //      소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다.

    // DIP : 프로그래머는 추상화에 의존해야지, 구체화에 의존하면 안된다. 의존성 주입은 이 원칙을 따르는 방법 중 하나다.

    // SRP : 단일 책임 원칙, 한 클래스는 하나의 책임만 가져야 한다.
    //      AppConfig는 구성 객체를 생성하고 연결하는 책임, 클라이언트 객체는 실행하는 책임만 담당

    // IoC 컨테이너, DI 컨테이너 : AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을IoC 컨테이너 또는 DI 컨테이너라 한다.
}
