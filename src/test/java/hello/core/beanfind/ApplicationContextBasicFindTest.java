package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findByBeanName() {
        // 조회할 빈 이름 입력 후 memberService 변수에 담는다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // 빈 이름으로 조회한 정보가 담긴 memberService 변수와 실제 구현 객체인 MemberSerivceImpl 객체와 동일한지 비교
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findByBeanType() {
        MemberService memberService = ac.getBean(MemberService.class);
        // 빈 이름으로 조회한 정보가 담긴 memberService 변수와 실제 구현 객체인 MemberSerivceImpl 객체와 동일한지 비교
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findByBeanName2() {
        // 위와 다르게 인터페이스 객체를 전달하는게 아니라 구현 객체를 전달하여 조회
        // 안좋은점은 구현 객체에 의존하면 안된다는것을 떠올리자.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 빈 이름으로 조회한 정보가 담긴 memberService 변수와 실제 구현 객체인 MemberSerivceImpl 객체와 동일한지 비교
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findByBeanNameX() {
        // MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        // 잘못된 빈 이름으로 조회할 때 예외발생 여부 확인
        assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("xxxx", MemberService.class));
    }

}
