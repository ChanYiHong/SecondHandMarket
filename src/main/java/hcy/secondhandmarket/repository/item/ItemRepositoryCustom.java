package hcy.secondhandmarket.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Object[] getItemById(Long itemId);

    Page<Object[]> getItemList(Pageable pageable);

}
