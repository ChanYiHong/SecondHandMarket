package hcy.secondhandmarket.dto.item;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@ToString
public class ItemSaveDto {

    private String email; // 사용자 이메일.
    private String title; // 상품명.
    private Integer price; // 상품 가격.
    private String description; // 상품 설명.
    private Long categoryId; // 카테고리 id.
    private Long sellingAreaId; // 판매 장소 id.

}
