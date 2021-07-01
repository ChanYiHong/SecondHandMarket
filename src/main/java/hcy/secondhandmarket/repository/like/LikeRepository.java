package hcy.secondhandmarket.repository.like;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Like;
import hcy.secondhandmarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // Item과 Member로 Like 찾기.
    Optional<Like> findByItemAndMember(Item item, Member member);

    // Item에 있는 Like 개수 찾기.
    @Query("select count(l) from Like l left join l.item i where i.id = :itemId")
    Long findNumberOfLikeByItemId(@Param("itemId") Long itemId);

}
