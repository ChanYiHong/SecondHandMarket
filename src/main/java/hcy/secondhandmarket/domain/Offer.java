package hcy.secondhandmarket.domain;

import hcy.secondhandmarket.converter.OfferStatusConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"item", "member"})
public class Offer extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 제시 가격.
    private Integer offerPrice;

    // 간단한 메세지.
    private String message;

    // 요청 상태.
    @Convert(converter = OfferStatusConverter.class)
    private OfferStatus offerStatus;

    /** 요청 거절 **/
    public void denyOffer() {
        this.offerStatus = OfferStatus.DENY;
    }

    /** 네고 시작 **/
    public void negoOffer() {
        this.offerStatus = OfferStatus.NEGO;
    }

}
