package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    EntityManager em;

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

    @Test
    @Transactional
    @Rollback
    public void findBySearchCondTest() throws Exception {
        //given
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setTitle("웅아");
        itemSearch.setEmail("user1@hcy.com");

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        //when

//        Item money = Item.builder()
//                .title("money")
//                .status(Status.NEW)
//                .sellPrice(10000)
//                .build();
//
//        itemRepository.save(money);

        System.out.println("==================================");
        System.out.println("==================================");
        System.out.println("==================================");
        System.out.println("==================================");

        Page<Object[]> result = itemRepository.findBySearchCond(itemSearch, pageRequestDTO.getPageable(Sort.by("id").ascending()));

        for (Object[] objects : result) {
            System.out.println((Item) objects[0]);
        }

//        System.out.println("==================================");
//        System.out.println("==================================");
//        System.out.println("==================================");
//        System.out.println("==================================");
//        System.out.println("직접.");
//
//
//        List<Item> resultList = em.createQuery("select i from Item i where i.title = :title", Item.class)
//                .setParameter("title", "웅아")
//                .getResultList();
//
//        for (Item item : resultList) {
//            System.out.println(item);
//        }

    }

}