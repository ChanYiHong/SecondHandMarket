package hcy.secondhandmarket.service.like;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Like;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.like.LikeRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LikeServiceTest {

    @Autowired LikeService likeService;
    @Autowired LikeRepository likeRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Rollback
    void findLikeNumberTest(){

        Item item = itemRepository.findById(1L).orElseThrow();

        likeRepository.save(new Like(null, item));

        Long result = likeRepository.findNumberOfLikeByItemId(1L);

        Assertions.assertEquals(1, result);
    }

}