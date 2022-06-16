package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /*
            basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
            basePackages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도있다.
            basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
            만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
         */
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig가 안읽히게 필터링
)
public class AutoAppConfig {

    /**
     * 컴포넌트 스캔 기본 대상
     * 컴포넌트 스캔은 @Component 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
     * @Component : 컴포넌트 스캔에서 사용
     * @Controlller : 스프링 MVC 컨트롤러에서 사용
     * @Service : 스프링 비즈니스 로직에서 사용
     * @Repository : 스프링 데이터 접근 계층에서 사용
     * @Configuration : 스프링 설정 정보에서 사용
     */

    /**
     * > 참고: 사실 애노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션을 들고
     * 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.
     *
     * 컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 스프링은 부가 기능을 수행한다.
     * @Controller : 스프링 MVC 컨트롤러로 인식
     * @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
     * @Configuration : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가처리를 한다.
     * @Service : 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에
     * 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
     *
     * > 참고: useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
     * 그냥 이런 옵션이 있구나 정도 알고 넘어가자
     */
}
