package springreview.demo.repository;

import springreview.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA 회원 레포지토리
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    Optional<Member> findByName(String name);

}
