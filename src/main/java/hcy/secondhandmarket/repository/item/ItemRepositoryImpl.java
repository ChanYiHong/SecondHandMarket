package hcy.secondhandmarket.repository.item;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.repository.CustomQuerydslUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hcy.secondhandmarket.domain.QCategory.category;
import static hcy.secondhandmarket.domain.QEmdArea.emdArea;
import static hcy.secondhandmarket.domain.QItem.item;
import static hcy.secondhandmarket.domain.QItemImage.itemImage;
import static hcy.secondhandmarket.domain.QLike.like;
import static hcy.secondhandmarket.domain.QMember.member;
import static hcy.secondhandmarket.domain.QSidoArea.sidoArea;
import static hcy.secondhandmarket.domain.QSiggArea.siggArea;

@RequiredArgsConstructor
@Slf4j
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Object[] getItemById(Long itemId) {
        Tuple tuple = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea, like.count())
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .leftJoin(like).on(like.item.eq(item))
                .where(item.id.eq(itemId))
                .groupBy(item)
                .fetchOne();

        Object[] objects = tuple.toArray();

        return objects;
    }

    @Override
    public Page<Object[]> getItemList(Pageable pageable) {

        QueryResults<Tuple> result = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea, like.count())
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .leftJoin(like).on(like.item.eq(item))
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .groupBy(item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, total);

    }

    @Override
    public Page<Object[]> findBySearchCond(ItemSearch itemSearch, Pageable pageable) {
        QueryResults<Tuple> result = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea, like.count())
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .leftJoin(like).on(like.item.eq(item))
                .where(
                        titleEq(itemSearch.getTitle())
                )
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, total);

    }

    @Override
    public List<Object[]> findMostLikeItemList(Pageable pageable) {
        QueryResults<Tuple> result = queryFactory
                .select(item, category, member, itemImage, emdArea, siggArea, sidoArea, like.count())
                .from(item)
                .leftJoin(item.category, category)
                .leftJoin(item.member, member)
                .leftJoin(itemImage).on(itemImage.item.eq(item))
                .leftJoin(item.sellingArea, emdArea)
                .leftJoin(emdArea.siggArea, siggArea)
                .leftJoin(siggArea.sidoArea, sidoArea)
                .leftJoin(like).on(like.item.eq(item))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Tuple> content = result.getResults();
        List<Object[]> collect = content.stream().map(Tuple::toArray).collect(Collectors.toList());
        return collect;
    }

    private BooleanExpression titleEq(String title) {
        log.info("?????? ?????? : {}",title);
        log.info("{}",StringUtils.hasText(title));
        return StringUtils.hasText(title) ? item.title.eq(title) : null;
    }

    private BooleanExpression emailEq(String email) {
        log.info("?????? ????????? : {}",email);
        return StringUtils.hasText(email) ? member.email.eq(email) : null;
    }

    // Sorting ??????.. ?????? ??????.
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
