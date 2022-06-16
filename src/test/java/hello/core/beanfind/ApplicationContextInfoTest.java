package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 등록된 모든 빈의 이름을 배열에 담는다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // forEach로 하나씩 꺼내보기
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        // 등록된 모든 빈의 이름을 배열에 담는다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // forEach로 하나씩 꺼내보기
        for (String beanDefinitionName : beanDefinitionNames) {

            // bean에 대한 metadata 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION : 내가 등록한 Bean (사용자 정의 빈에 해당)
            // ROLE_INFRASTRUCTURE : 스플링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == beanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " objec = " + bean);
            }
        }
    }
}
