package hcy.secondhandmarket.dto.member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberInfoDTO {

    // Member 정보.
    private String email;
    private String nickname;
    private String phoneNumber;

    // 내가 쓴 댓글 정보.
    private Integer reviewCnt;

    // 내가 쓴 판매 글 정보.
    private Integer postCnt;

}
