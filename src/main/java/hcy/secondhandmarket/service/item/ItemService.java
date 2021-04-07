package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;

public interface ItemService {

    // 상품 저장.
    Long save(ItemSaveDTO itemSaveDto);

    // 상품 단건 조회.
    ItemResponseDTO getOne(Long itemId);

    default Item dtoToEntity(ItemSaveDTO itemSaveDto) {
        Member member = Member.builder().email(itemSaveDto.getEmail()).build();
        EmdArea emdArea = EmdArea.builder().id(itemSaveDto.getSellingAreaId()).build();
        Category category = Category.builder().id(itemSaveDto.getCategoryId()).build();

        return Item.builder()
                .title(itemSaveDto.getTitle())
                .sellPrice(itemSaveDto.getPrice())
                .description(itemSaveDto.getDescription())
                .member(member)
                .category(category)
                .sellingArea(emdArea)
                .status(Status.NEW)
                .build();
    }

    default ItemResponseDTO entityToDTO(Item item, Member member, Category category,
                                        EmdArea emdArea, SiggArea siggArea, SidoArea sidoArea) {
        return ItemResponseDTO.builder()
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .rating(member.getRating())
                .category(category.getName())
                .emdArea(emdArea.getName())
                .siggArea(siggArea.getName())
                .sidoArea(sidoArea.getName())
                .title(item.getTitle())
                .sellPrice(item.getSellPrice())
                .viewCount(item.getViewCount())
                .description(item.getDescription())
                .status(item.getStatus())
                .build();
    }

}
