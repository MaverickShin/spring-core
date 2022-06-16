package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void stateFulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TreadA : A 사용자 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);

        // TreadB : B 사용자 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        // TreadA : 사용자 A 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAprice);

        // statefulService1은 만원이여야 하는데 2만원이 되어버림
        assertThat(userAprice).isNotEqualTo(userBprice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}