package hcy.secondhandmarket.repository.member;

import hcy.secondhandmarket.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /** 사용자 email 과 소셜 여부를 통해 사용자를 가져옴. Role을 left outer join으로 가져오도록 EntityGraph 설계... (fetch join) **/
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.fromSocial = :social and m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);


    /** 사용자 정보 창 **/
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m, count(i) from Member m left join Item i on i.member = m " +
            "where m.email = :email")
    List<Object[]> findByEmailWithCnt(@Param("email") String email);

}
