package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.dto.itemimage.ItemImageDTO;
import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ItemService {

    // 상품 저장.
    Long save(ItemSaveDTO itemSaveDto, MemberDTO memberDTO);

    // 상품 단건 조회.
    ItemResponseDTO getOne(Long itemId);

    // 아이템과, 아이템 이미지 같이 처리.
    default Map<String, Object> dtoToEntity(ItemSaveDTO itemSaveDto, Member member, EmdArea area, Category category) {
        //Member member = Member.builder().email(itemSaveDto.getEmail()).build();
        //EmdArea emdArea = EmdArea.builder().id(itemSaveDto.getSellingAreaId()).build();
        //Category category = Category.builder().id(itemSaveDto.getCategoryId()).build();

        //Member member = Member.builder().email(memberDTO.getEmail()).build();

        Map<String, Object> entityMap = new HashMap<>();


        Item item = Item.builder()
                .title(itemSaveDto.getTitle())
                .sellPrice(itemSaveDto.getSellPrice())
                .description(itemSaveDto.getDescription())
                .member(member)
                .category(category)
                .sellingArea(area)
                .status(Status.NEW)
                .build();

        entityMap.put("item", item);

        List<ItemImageDTO> imageDTOList = itemSaveDto.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0) {
            List<ItemImage> itemImageList = imageDTOList.stream().
                    map(itemImageDTO -> {
                        ItemImage itemImage = ItemImage.builder()
                                .path(itemImageDTO.getPath())
                                .uuid(itemImageDTO.getUuid())
                                .imgName(itemImageDTO.getImgName())
                                .item(item)
                                .build();

                        return itemImage;
                    }).collect(Collectors.toList());

            entityMap.put("imgList", itemImageList);
        }

        return entityMap;
    }

    default ItemResponseDTO entityToDTO(Item item, Member member, Category category, List<ItemImage> imageList,
                                        EmdArea emdArea, SiggArea siggArea, SidoArea sidoArea) {

        List<ItemImageDTO> imageDTOList = imageList.stream().map(itemImage -> {
            return ItemImageDTO.builder()
                    .imgName(itemImage.getImgName())
                    .path(itemImage.getPath())
                    .uuid(itemImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        ItemResponseDTO itemResponseDTO = ItemResponseDTO.builder()
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

        itemResponseDTO.setImageDTOList(imageDTOList);

        return itemResponseDTO;
    }

}
