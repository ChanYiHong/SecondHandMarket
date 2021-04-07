package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.sidoarea.SidoAreaRepository;
import hcy.secondhandmarket.repository.siggarea.SiggAreaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SidoAreaRepository sidoAreaRepository;
    @Autowired
    SiggAreaRepository siggAreaRepository;
    @Autowired
    EmdAreaRepository emdAreaRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        SidoArea sidoArea = SidoArea.builder().name("서울시").build();
        sidoAreaRepository.save(sidoArea);

        SiggArea siggArea = SiggArea.builder().name("송파구").sidoArea(sidoArea).build();
        siggAreaRepository.save(siggArea);

        EmdArea emdArea = EmdArea.builder().name("잠실2동").siggArea(siggArea).build();
        emdAreaRepository.save(emdArea);

        Category category = Category.builder().name("인형").build();
        categoryRepository.save(category);

        Member member = Member.builder().email("userA@aaa.com").name("hcy")
                .phoneNumber("010-9955-9135").password("1234").rating(5).build();
        memberRepository.save(member);
    }

    @Test
    @Transactional
    @Rollback
    public void saveItem() throws Exception {
        ItemSaveDTO itemDto = ItemSaveDTO.builder()
                .title("애웅이")
                .price(1000000)
                .categoryId(1L)
                .email("userA@aaa.com")
                .description("웅아가 좋아하는 인형입니다.")
                .sellingAreaId(1L)
                .build();

        Long itemId = itemService.save(itemDto);

        Item findItem = itemRepository.getOne(itemId);

        assertThat(findItem.getId()).isEqualTo(itemId);
        assertThat(findItem.getTitle()).isEqualTo(itemDto.getTitle());

        System.out.println(findItem);

    }

    @Test
    @Transactional
    @Rollback
    public void findItemById() throws Exception {

        ItemSaveDTO itemDto = ItemSaveDTO.builder()
                .title("애웅이")
                .price(1000000)
                .categoryId(1L)
                .email("userA@aaa.com")
                .description("웅아가 좋아하는 인형입니다.")
                .sellingAreaId(1L)
                .build();

        Long itemId = itemService.save(itemDto);

        Object result = itemRepository.getItemById(itemId);
        Object[] resultArr = (Object[]) result;

        System.out.println("길이 : " + resultArr.length);

        for (Object o : resultArr) {
            System.out.println(o);
        }
    }

    @AfterEach
    void afterEach() {

    }

}