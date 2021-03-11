package springreview.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springreview.demo.domain.Member;
import springreview.demo.repository.MemberRepository;
import springreview.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional  // 해당 클래스 메소드 시 트랜잭션 시작. 정상 종료 시 트랜잭션 커밋 // 런타임 에러 발생 시 롤백하도록 설정
public class MemberService {

    // @1-1 기존에는 회원 서비스가 메모리회원 리포지토리를 직접 생성하게 했지만
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // @1-2 이제는 회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경한다.
    private final MemberRepository memberRepository;

    // @1-3 Setter 형식으로 넣을 수 있게 해준다.
    //      이렇게 하면 의존관계를 자동으로 주입하게 된다.
    // @Autowired  // 생성자가 1개라면 Autowired 생략 가능
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 멤버 하나만 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
