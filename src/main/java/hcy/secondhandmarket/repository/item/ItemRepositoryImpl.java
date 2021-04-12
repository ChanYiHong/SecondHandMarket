package hcy.secondhandmarket.repository.item;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hcy.secondhandmarket.domain.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static hcy.secondhandmarket.domain.QCategory.category;
import static hcy.secondhandmarket.domain.QEmdArea.emdArea;
import static hcy.secondhandmarket.domain.QItem.item;
import static hcy.secondhandmarket.domain.QItemImage.itemImage;
import static hcy.secondhandmarket.domain.QMember.member;
import static hcy.secondhandmarket.domain.QSidoArea.sidoArea;
import static hcy.secondhandmarket.domain.QSiggArea.siggArea;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Object[] getItemById(Long itemId) {
        Tuple tuple = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea)
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .join(emdArea.siggArea, siggArea)
                .join(siggArea.sidoArea, sidoArea)
                .where(item.id.eq(itemId))
                .groupBy(item)
                .fetchOne();

        Object[] objects = tuple.toArray();

        return objects;
    }
}
