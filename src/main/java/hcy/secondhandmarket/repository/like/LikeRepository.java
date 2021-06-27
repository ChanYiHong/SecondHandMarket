package hcy.secondhandmarket.repository.like;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Like;
import hcy.secondhandmarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByItemAndMember(Item item, Member member);

}
