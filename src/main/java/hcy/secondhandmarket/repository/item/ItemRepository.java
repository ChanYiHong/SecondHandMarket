package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom{

    // 상품 조회.
//    @EntityGraph(attributePaths = {"member", "category", "sellingArea"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("select i, m, c, s, sigg, sido from Item i left join i.member m left join i.category c left join i.sellingArea s " +
//            "left join SiggArea sigg on sigg.id = s.siggArea left join SidoArea sido on sido.id = sigg.sidoArea" +
//            " where i.id = :itemId")
//    Object[] findItemById(@Param("itemId") Long itemId);

//    @EntityGraph(attributePaths = {"member", "category", "sellingArea"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("select i, m, c, s from Item i left join i.member m left join i.category c left join i.sellingArea s " +
//            "where i.id = :itemId")
//    Object[] findItemById(@Param("itemId") Long itemId);

    // 상품 리스트.
//    @Query("select i, m, c, s, im from Item i left outer join i.member m left outer join i.category c left outer join i.sellingArea s " +
//            "left outer join ItemImage im on ItemImage.item = i group by i")
//    Page<Object[]> getListPage(Pageable pageable);

    // 상품 수정.

    // 상품 삭제.

    // 회원 email 로 아이템 리스트..

    //@EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m, i, im from Member m left join Item i on i.member = m left join ItemImage im on im.item = i where m.email = :email")
    Page<Object[]> getListByEmail(@Param("email") String email, Pageable pageable);

}
