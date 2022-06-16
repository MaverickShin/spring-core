package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        // MemberService memberService = new MemberServiceImpl();

        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // ApplicationContext를 스프링 컨테이너라고 한다.
        // 내가 만든 구성 제어 클래스를 파라미터로 넘겨줘서 생성해야 한다.
        // .class는 사용 가능한 클래스의 인스턴스가 없을 때 사용된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 매개변수 생성자로 member 인스턴스 생성
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 회원가입 메소드에 member 객체 전달
        memberService.join(member);

        // Member의 새로운 인스턴스를 생성하여 아이디로 조회된 멤버 객체를 전달
        Member findMember = memberService.findMember(1l);

        // member 변수에 저장된 데이터는 memberService 변수의
        // join 메소드로 전달 되었기 때문에 같은 데이터를 반환한다.
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
