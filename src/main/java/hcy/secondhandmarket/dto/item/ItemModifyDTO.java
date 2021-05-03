package hcy.secondhandmarket.dto.item;

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
public class ItemModifyDTO {

    private Long id;
    private String title;
    private Integer sellPrice;
    private String description;

    @Builder.Default
    private List<ItemImageDTO> itemImageDTOList = new ArrayList<>();

}
