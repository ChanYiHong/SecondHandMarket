package hcy.secondhandmarket.dto.review;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewModifyDTO {

    private Long id;
    private String content;
    private Integer rating;

}
