package hcy.secondhandmarket.dto.member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberSaveDTO {

    private String email;
    private String password;
    private String phoneNumber;
    private String name;

}
