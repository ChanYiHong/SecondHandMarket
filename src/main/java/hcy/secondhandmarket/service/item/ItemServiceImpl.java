package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemModifyDTO;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.dto.itemimage.ItemImageDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.itemimage.ItemImageRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final MemberRepository memberRepository;
    private final EmdAreaRepository emdAreaRepository;
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    public Long save(ItemSaveDTO itemSaveDto, MemberDTO memberDTO) {

        log.info("Saving Item : {}", itemSaveDto);

        /** JPA에서 엔티티를 저장할 때, 연관된 모든 엔티티는 "영속 상태"여야 한다!
         * 따라서 연관된 모든 엔티티를 영속 상태로 만듬. (DB에서 가져옴)**/
        // member
        Optional<Member> memberOptional = memberRepository.findByEmail(memberDTO.getEmail(), memberDTO.isFromSocial());

        if(memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Check out email or password!");
        }

        Member member = memberOptional.get();

        // emd (읍면동)
        EmdArea area = emdAreaRepository.findById(itemSaveDto.getSellingAreaId()).get();

        // category
        Category category = categoryRepository.findById(itemSaveDto.getCategoryId()).get();

        Map<String, Object> entityMap = dtoToEntity(itemSaveDto, member, area, category);

        Item item = (Item) entityMap.get("item");

        itemRepository.save(item);

        List<ItemImage> itemImageList = (List<ItemImage>) entityMap.get("imgList");

        if(itemImageList != null) {
            itemImageList.forEach(itemImage -> {
                itemImageRepository.save(itemImage);
            });
        }

        return item.getId();
    }

    @Override
    public ItemResponseDTO getOne(Long itemId) {
        Object[] result = itemRepository.getItemById(itemId);
        return entityToDTO((Item) result[0], (Category) result[1], (Member) result[2], (List<ItemImage>) Arrays.asList((ItemImage)result[3]),
                (EmdArea) result[4], (SiggArea) result[5], (SidoArea) result[6]);
    }

    @Override
    public PageResponseDTO<Object[], ItemResponseDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = itemRepository.getItemList(pageable);

        Function<Object[], ItemResponseDTO> fn = entity -> {
            return entityToDTO((Item) entity[0], (Category) entity[1], (Member) entity[2], (List<ItemImage>) Arrays.asList((ItemImage)entity[3]),
                    (EmdArea) entity[4], (SiggArea) entity[5], (SidoArea) entity[6]);
        };

        return new PageResponseDTO<>(fn, result);

    }

    @Override
    public PageResponseDTO<Object[], ItemResponseDTO> getListByEmail(String email, PageRequestDTO pageRequestDTO) {

        log.info("getListByEmail Service email : {}, pageRequestDTO : {}", email, pageRequestDTO);

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = itemRepository.getListByEmail(email, pageable);

        Function<Object[], ItemResponseDTO> fn = entity -> {
            return entityToDTO((Member) entity[0], (Item) entity[1], (List<ItemImage>) Arrays.asList((ItemImage) entity[2]));
        };

        return new PageResponseDTO<>(fn, result);

    }

    @Transactional
    @Override
    public void modifyItem(ItemModifyDTO itemModifyDTO) {
        Optional<Item> result = itemRepository.findById(itemModifyDTO.getId());

        if(result.isEmpty()) {
           throw new IllegalArgumentException("There is no item : " + itemModifyDTO.getTitle());
        }

        Item item = result.get();

        item.changeTitle(itemModifyDTO.getTitle());
        item.changeDescription(itemModifyDTO.getDescription());
        item.changeSellPrice(itemModifyDTO.getSellPrice());

        List<ItemImageDTO> itemImageDTOList = itemModifyDTO.getItemImageDTOList();

        if(itemImageDTOList.size() > 0 && itemImageDTOList != null) {
            List<ItemImage> itemImages = itemImageDTOList.stream()
                    .map(itemImageDTO -> {
                        return ItemImage.builder()
                                .uuid(itemImageDTO.getUuid())
                                .path(itemImageDTO.getPath())
                                .imgName(itemImageDTO.getImgName())
                                .item(item)
                                .build();
                    }).collect(Collectors.toList());

            for (ItemImage itemImage : itemImages) {
                itemImageRepository.save(itemImage);
            }
        }
    }

    @Override
    @Transactional
    public void removeItem(Long id) {

        Optional<Item> result = itemRepository.findById(id);

        if(result.isEmpty()) {
            throw new IllegalArgumentException("Could not find Item : " + id);
        }

        Item item = result.get();

        List<ItemImage> itemImages = itemImageRepository.findByItemId(id);

        for (ItemImage itemImage : itemImages) {
            itemImageRepository.deleteById(itemImage.getId());
        }

        itemRepository.deleteById(id);
    }
}
