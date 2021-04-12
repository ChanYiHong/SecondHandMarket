package hcy.secondhandmarket.dto.item;

import hcy.secondhandmarket.domain.Status;
import hcy.secondhandmarket.dto.itemimage.ItemImageDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ItemResponseDTO {

    /** 사용자 정보. **/
    private String email; // 사용자 이메일.
    private String phoneNumber; // 사용자 폰번.
    private int rating; // 사용자 신뢰도 척도.

    /** 카테고리 정보. **/
    private String category; // 카테고리 이름.

    /** 지역 정보. **/
    private String emdArea; // 읍면동 이름.
    private String siggArea; // 시군구 이름.
    private String sidoArea; // 시도 이름.

    /** 상품 정보. **/
    private String title; // 상품명.
    private Integer sellPrice; // 상품 가격.
    private int viewCount; // 조회수.
    private String description; // 상품 설명.
    private Status status; // 상품 상황. (새로움, 예약됨, 판매완료).

    /** 상품 사진. **/
    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>();

}
