package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    // MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given : 주워진 조건
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 실행 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then : 결과치 (기대치)
        // member와 findMember가 동일한지 비교한다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
