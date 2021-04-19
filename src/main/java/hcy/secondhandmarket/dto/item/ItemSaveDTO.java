package hcy.secondhandmarket.dto.item;

import hcy.secondhandmarket.dto.itemimage.ItemImageDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@ToString
public class ItemSaveDTO {

    //private String email; // 사용자 이메일.
    private String title; // 상품명.
    private Integer sellPrice; // 상품 가격.
    private String description; // 상품 설명.
    private Long categoryId; // 카테고리 id.
    private Long sellingAreaId; // 판매 장소 id.

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>(); // 상품 이미지들...

}
