package hcy.secondhandmarket.dto.review;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewSaveDTO {

    private String content;
    private Integer rating;
    private Long itemId;

}
