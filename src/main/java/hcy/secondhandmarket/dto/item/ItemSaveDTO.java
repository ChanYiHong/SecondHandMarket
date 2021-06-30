package hcy.secondhandmarket.dto.item;

import hcy.secondhandmarket.dto.itemimage.ItemImageDTO;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@ToString
public class ItemSaveDTO {

    //private String email; // 사용자 이메일.

    @NotBlank
    @NotNull
    private String title; // 상품명.

    @NotNull
    @Range(min = 1000, max = 10000000)
    private Integer sellPrice; // 상품 가격.

    @NotBlank
    private String description; // 상품 설명.

    @NotNull
    private Long categoryId; // 카테고리 id.

    @NotNull
    private Long sellingAreaId; // 판매 장소 id. (실제로 Service에서 사용)

    @NotNull
    private Long sidoId; // 검증 시에만 사용

    @NotNull
    private Long siggId; // 검증 시에만 사용

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>(); // 상품 이미지들...

}
