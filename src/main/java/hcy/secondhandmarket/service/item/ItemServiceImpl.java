package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.itemimage.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final ItemImageRepository itemImageRepository;


    @Override
    @Transactional
    public Long save(ItemSaveDTO itemSaveDto) {

        log.info("Saving Item : {}", itemSaveDto);

        Map<String, Object> entityMap = dtoToEntity(itemSaveDto);

        Item item = (Item) entityMap.get("item");
        itemRepository.save(item);

        List<ItemImage> itemImageList = (List<ItemImage>) entityMap.get("imgList");

        itemImageList.forEach(itemImage -> {
            itemImageRepository.save(itemImage);
        });

        return item.getId();
    }

    @Override
    public ItemResponseDTO getOne(Long itemId) {
        Object[] result = itemRepository.findItemById(itemId);
        return entityToDTO((Item) result[0], (Member) result[1], (Category) result[2], (List<ItemImage>) result[3],
                (EmdArea) result[3], (SiggArea) result[4], (SidoArea) result[5]);
    }
}
