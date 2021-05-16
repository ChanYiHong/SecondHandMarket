package hcy.secondhandmarket.dto.offer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OfferSaveDTO {

    private String email;
    private Long itemId;
    private Integer offerPrice;
    private String message;

}
