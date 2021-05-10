package hcy.secondhandmarket.dto.review;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewResponseDTO {

    private Long id;
    private String content;
    private String writer;
    private Integer rating;

}
