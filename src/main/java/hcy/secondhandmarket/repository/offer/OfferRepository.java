package hcy.secondhandmarket.repository.offer;

import hcy.secondhandmarket.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o, i, m from Offer o left join o.item i left join o.member m where i.id = :itemId")
    Page<Object[]> getListByItemId(Pageable pageable, @Param("itemId") Long itemId);

    @Query("select o, i, m from Offer o left join o.item i left join o.member m where i.member.email = :email")
    Page<Object[]> getListByItemOwnerEmail(@Param("email") String email, Pageable pageable);

}
