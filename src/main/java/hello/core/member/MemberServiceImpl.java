package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // (롬복 라이브러리 이다.) final이 붙은 필드를 모아서생성자를 자동으로 만들어준다.
public class MemberServiceImpl implements MemberService{

    /*
        생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고,
        순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
        기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다.
        생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
        항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지 않는게 좋다
     */

    private final MemberRepository memberRepository;

    /*
    @Autowired // 생성자 주입에서의 Autowired는 생성자가 하나면 생략할 수 있다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // RequiredArgsConstructor 어노테이션이 생성자를 자동으로 만들어 준다.
    */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
