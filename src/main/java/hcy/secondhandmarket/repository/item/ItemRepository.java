package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.Item;
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

    @EntityGraph(attributePaths = {"member", "category", "sellingArea"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select i, m, c, s from Item i left join i.member m left join i.category c left join i.sellingArea s " +
            "where i.id = :itemId")
    Object[] findItemById(@Param("itemId") Long itemId);

    // 상품 수정.

    // 상품 삭제.

}
