package hcy.secondhandmarket.dto.offer;

import hcy.secondhandmarket.domain.OfferStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OfferResponseDTO {

    private Long id;
    private Integer offerPrice;
    private String message;
    private OfferStatus offerStatus;

    private String nickname;

    private Long itemId;
    private String title;

}
