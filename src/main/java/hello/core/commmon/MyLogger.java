package hello.core.commmon;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다.
// proxyMode = ScopedProxyMode.TARGET_CLASS를 사용하면 request scope 빈의 생성을 지연할 수 있다.
// 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS 를 선택
// 적용 대상이 인터페이스면 INTERFACES 를 선택
// 이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시
// 클래스를 다른 빈에 미리 주입해 둘 수 있다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + "request scope bean close " + this);
    }
}
