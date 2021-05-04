package hcy.secondhandmarket.repository.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.repository.CustomQuerydslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .where(item.id.eq(itemId))
                .groupBy(item)
                .fetchOne();

        Object[] objects = tuple.toArray();

        return objects;
    }

    @Override
    public Page<Object[]> getItemList(Pageable pageable) {

        QueryResults<Tuple> result = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea)
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, total);

    }

    // Sorting 조건.. 확장 가능.
    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(order -> {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            switch (order.getProperty()) {
                case "id" :
                    OrderSpecifier<?> orderId = CustomQuerydslUtils.getSortedColumn(direction, item, "id");
                    orders.add(orderId);
                    break;
                default :
                    break;
            }
        });

        return orders;
    }
}
