package hcy.secondhandmarket.repository.offer;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Offer;
import hcy.secondhandmarket.domain.OfferStatus;
import hcy.secondhandmarket.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OfferRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    OfferRepository offerRepository;

    @Test
    @Rollback
    @Transactional
    public void getListByItemIdTest() throws Exception {
        //given

        Item item = Item.builder()
                .title("임시")
                .sellPrice(100000)
                .status(Status.NEW)
                .build();

        em.persist(item);

        Offer offer1 = Offer.builder()
                .offerPrice(10000)
                .message("1번요청")
                .offerStatus(OfferStatus.WAIT)
                .item(item)
                .member(null)
                .build();

        Offer offer2 = Offer.builder()
                .offerPrice(20000)
                .message("2번요청")
                .offerStatus(OfferStatus.WAIT)
                .item(item)
                .member(null)
                .build();

        offerRepository.save(offer1);
        offerRepository.save(offer2);

        em.flush();
        em.clear();

        //when

        Pageable pageable = PageRequest.of(0, 10);

        Page<Object[]> result = offerRepository.getListByItemId(pageable, item.getId());

        //then

        List<Object[]> content = result.getContent();

        for (Object[] objects : content) {
            Offer findOffer = (Offer) objects[0];
            Item findItem = (Item) objects[1];

            System.out.println(findOffer);
            System.out.println(findItem);
        }

    }

}