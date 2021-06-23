package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Object[] getItemById(Long itemId);

    Page<Object[]> getItemList(Pageable pageable);

    /** Item title, 게시자 email로 Item 검색 **/
    Page<Object[]> findBySearchCond(ItemSearch itemSearch, Pageable pageable);

}
