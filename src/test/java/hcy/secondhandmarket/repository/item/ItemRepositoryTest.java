package hcy.secondhandmarket.repository.item;

import hcy.secondhandmarket.domain.*;
import hcy.secondhandmarket.dto.item.ItemSaveDto;
import hcy.secondhandmarket.repository.category.CategoryRepository;
import hcy.secondhandmarket.repository.emdarea.EmdAreaRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.sidoarea.SidoAreaRepository;
import hcy.secondhandmarket.repository.siggarea.SiggAreaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

}