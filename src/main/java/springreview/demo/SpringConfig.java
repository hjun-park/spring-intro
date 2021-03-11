package springreview.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springreview.demo.aop.TimeTraceAop;
import springreview.demo.repository.MemberRepository;
import springreview.demo.service.MemberService;

// 각 파일에 @Service, @Repository, @Autowired 애노테이션을 집어넣었는데
   // -> 컴포넌트 스캔 방식 ( 이 방식을 선호 )
// 이렇게 Config 파일로 관리하면 애노테이션 붙일 필요 없이 할 수 있다.
   // -> 자바 코드로 스프링 빈 설정
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    // 스프링 빈 등록
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

}
