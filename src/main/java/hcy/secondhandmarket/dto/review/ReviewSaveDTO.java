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
    private String email;
    private Integer rating;
    private Long itemId;

}
