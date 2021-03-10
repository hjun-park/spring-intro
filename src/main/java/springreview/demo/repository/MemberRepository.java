package springreview.demo.repository;

import springreview.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    /*
        Optional<T> 클래스 : Integer나 Double 클래스처럼 T 타입의 객체를 포장해주는 Wrapper 클래서
                            따라서 Optional Instance는 "모든 타입의 참조 변수" 저장 가능
     */

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
