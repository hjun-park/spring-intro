package springreview.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springreview.demo.repository.JpaMemberRepository;
import springreview.demo.repository.MemberRepository;
import springreview.demo.repository.MemoryMemberRepository;
import springreview.demo.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 각 파일에 @Service, @Repository, @Autowired 애노테이션을 집어넣었는데
   // -> 컴포넌트 스캔 방식 ( 이 방식을 선호 )
// 이렇게 Config 파일로 관리하면 애노테이션 붙일 필요 없이 할 수 있다.
   // -> 자바 코드로 스프링 빈 설정
@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    private final EntityManager em;

//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }
}
