package hcy.secondhandmarket.domain;

import hcy.secondhandmarket.converter.StatusConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"category", "sellingArea", "member"})
public class Item extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer sellPrice;

    private int viewCount;

    private String description;

    @Convert(converter = StatusConverter.class)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmdArea sellingArea;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }



}
