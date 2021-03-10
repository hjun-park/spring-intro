package springreview.demo;

import org.springframework.context.annotation.Bean;
import springreview.demo.repository.MemberRepository;
import springreview.demo.repository.MemoryMemberRepository;
import springreview.demo.service.MemberService;

// 각 파일에 @Service, @Repository, @Autowired 애노테이션을 집어넣었는데
   // -> 컴포넌트 스캔 방식 ( 이 방식을 선호 )
// 이렇게 Config 파일로 관리하면 애노테이션 붙일 필요 없이 할 수 있다.
   // -> 자바 코드로 스프링 빈 설정
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
