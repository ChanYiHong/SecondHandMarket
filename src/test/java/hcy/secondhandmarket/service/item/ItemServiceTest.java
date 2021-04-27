package hcy.secondhandmarket.service.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.dto.member.MemberSaveDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.sidoarea.SidoAreaRepository;
import hcy.secondhandmarket.repository.siggarea.SiggAreaRepository;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.security.service.MemberDetailsService;
import hcy.secondhandmarket.service.member.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    EntityManager em;

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
    MemberService memberService;

    @Autowired
    MemberDetailsService memberDetailsService;

    @BeforeEach
    void beforeEach() {
        SidoArea sidoArea = SidoArea.builder().name("서울시").build();
        //sidoAreaRepository.save(sidoArea);

        em.persist(sidoArea);

        SiggArea siggArea = SiggArea.builder().name("송파구").sidoArea(sidoArea).build();
        //siggAreaRepository.save(siggArea);

        em.persist(siggArea);

        EmdArea emdArea = EmdArea.builder().name("잠실2동").siggArea(siggArea).build();
        //emdAreaRepository.save(emdArea);

        em.persist(emdArea);

        Category category = Category.builder().name("인형").build();
        //categoryRepository.save(category);

        em.persist(category);
    }

    @Test
    @Transactional
    @Rollback
    public void saveItem() throws Exception {
        ItemSaveDTO itemDto = ItemSaveDTO.builder()
                .title("애웅이")
                .sellPrice(1000000)
                .categoryId(1L)
                .description("웅아가 좋아하는 인형입니다.")
                .sellingAreaId(1L)
                .build();


        MemberSaveDTO memberSaveDTO = MemberSaveDTO.builder()
                .email("user1").password("1234").name("hcy").phoneNumber("010").build();

        memberService.join(memberSaveDTO);

        MemberDTO memberDTO = (MemberDTO) memberDetailsService.loadUserByUsername("user1");

        Long itemId = itemService.save(itemDto, memberDTO);

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
                .sellPrice(1000000)
                .categoryId(1L)
                .description("웅아가 좋아하는 인형입니다.")
                .sellingAreaId(1L)
                .build();


        MemberSaveDTO memberSaveDTO = MemberSaveDTO.builder()
                .email("user1").password("1234").name("hcy").phoneNumber("010").build();

        memberService.join(memberSaveDTO);

        MemberDTO memberDTO = (MemberDTO) memberDetailsService.loadUserByUsername("user1");


        Long itemId = itemService.save(itemDto, memberDTO);

        Object result = itemRepository.getItemById(itemId);
        Object[] resultArr = (Object[]) result;

        System.out.println("길이 : " + resultArr.length);

        for (Object o : resultArr) {
            System.out.println(o);
        }

    }

    @Test
    @Transactional
    @Rollback
    public void itemListTest() throws Exception {

        ItemSaveDTO itemDto = ItemSaveDTO.builder()
                .title("애웅이")
                .sellPrice(1000000)
                .categoryId(1L)
                .description("웅아가 좋아하는 인형입니다.")
                .sellingAreaId(1L)
                .build();

        ItemSaveDTO itemDto2 = ItemSaveDTO.builder()
                .title("웅아쪼아")
                .sellPrice(2000000)
                .categoryId(1L)
                .description("알러븅")
                .sellingAreaId(1L)
                .build();


        MemberSaveDTO memberSaveDTO = MemberSaveDTO.builder()
                .email("user1").password("1234").name("hcy").phoneNumber("010").build();

        memberService.join(memberSaveDTO);

        MemberDTO memberDTO = (MemberDTO) memberDetailsService.loadUserByUsername("user1");


        itemService.save(itemDto, memberDTO);
        itemService.save(itemDto2, memberDTO);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

        Page<Object[]> result = itemRepository.getListPage(pageable);

        List<Object[]> content = result.getContent();

        for (Object[] objects : content) {
            for (Object object : objects) {
                System.out.println(object);
            }
        }

    }

    @AfterEach
    void afterEach() {

    }

}