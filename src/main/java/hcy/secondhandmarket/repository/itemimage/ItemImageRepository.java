package hcy.secondhandmarket.repository.itemimage;

import hcy.secondhandmarket.domain.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

    @Query("select im from ItemImage im left join im.item i where i.id = :itemId")
    List<ItemImage> findByItemId(@Param("itemId") Long itemId);
}
