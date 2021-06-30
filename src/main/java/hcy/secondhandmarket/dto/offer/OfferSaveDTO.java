package hcy.secondhandmarket.dto.offer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OfferSaveDTO {

    private String email;
    private Long itemId;

    @NotNull
    private Integer offerPrice;

    @NotBlank
    private String message;

}
