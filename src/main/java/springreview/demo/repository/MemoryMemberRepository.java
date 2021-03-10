package springreview.demo.repository;

import org.springframework.stereotype.Repository;
import springreview.demo.domain.Member;

import java.util.*;


/*
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);           // 멤버 ID는 자동 증가
        store.put(member.getId(), member);  // Map(ID, 멤버객체)
        return member;
    }

    // Optional 객체 : http://www.tcpschool.com/java/java_stream_optional
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()                              // 스트림의 각 요소에 대해서
                .filter(member -> member.getName().equals(name))    // filter는 요소를 하나씩 평가해서 걸러내는 작업
                .findAny();     // findAny는 스트림에서 첫 번째 요소를 참조하는 Optional 객체 반환, 즉 걸러내지면 하나만 남게 된다.
                                //  반환은 Optional
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}


