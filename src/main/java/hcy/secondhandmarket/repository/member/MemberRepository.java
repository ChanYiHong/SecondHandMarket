package hcy.secondhandmarket.repository.member;

import hcy.secondhandmarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
