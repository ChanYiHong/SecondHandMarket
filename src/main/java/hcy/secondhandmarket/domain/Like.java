package hcy.secondhandmarket.domain;

import lombok.*;

import javax.persistence.*;

/** Item 좋아요 를 위한 table **/
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public Like(Member member, Item item) {
        this.member = member;
        this.item = item;
    }
}
