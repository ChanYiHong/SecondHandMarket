package hcy.secondhandmarket.repository.offer;

import hcy.secondhandmarket.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    // fetch join으로 한번에 select 로 가져와서 영속성 컨텍스트에 저장되기 때문에 item을 가져와도 쿼리가 추가로 안나가고 객체 그래프 탐색이 가능.
    @Query("select o from Offer o join fetch o.item i where o.id = :offerId")
    Optional<Offer> getOneWithItemByOfferId(@Param("offerId") Long id);

    @Query("select o, i, m from Offer o left join o.item i left join o.member m where i.id = :itemId")
    Page<Object[]> getListByItemId(Pageable pageable, @Param("itemId") Long itemId);

    @Query("select o, i, m from Offer o left join o.item i left join o.member m where i.member.email = :email")
    Page<Object[]> getListByItemOwnerEmail(@Param("email") String email, Pageable pageable);

}
