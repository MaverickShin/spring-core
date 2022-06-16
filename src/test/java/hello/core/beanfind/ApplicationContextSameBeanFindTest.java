package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        // MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository bean = ac.getBean("memberRepository", MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        
        // 조회된 bean을 Bean 이름 (key)과 정보(val)로 map에 저장
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        
        // 이터레이터로 key + value를 하나씩 찍어보기
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + " value = " + beansOfType.get(s));
        }
        
        System.out.println("beanOfType = " + beansOfType);
        
        // map에 담긴 bean 데이터의 갯수가 2개인지 비교
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class sameBeanConfig {
        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
