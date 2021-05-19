package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.ItemImage;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void getListByEmailTest() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        String email = "user1@hcy.com";

        Page<Object[]> result = itemRepository.getListByEmail(email, pageable);

        List<Object[]> content = result.getContent();

        System.out.println(content.size());


        for (Object[] objects : content) {

            Member member = (Member) objects[0];
            Item item = (Item) objects[1];
            ItemImage itemImage = (ItemImage) objects[2];

            System.out.println(member);
            System.out.println(item);
            System.out.println(itemImage);

        }

    }

}