package hcy.secondhandmarket.repository.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSearch {

    private String title;
//    private String email;

    /** 향후 검색조건 추가 **/

}
