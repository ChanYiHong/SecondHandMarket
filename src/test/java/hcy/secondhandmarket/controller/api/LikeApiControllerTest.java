package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Like;
import hcy.secondhandmarket.domain.Status;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.like.LikeRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class LikeApiControllerTest {

    @Autowired LikeRepository likeRepository;
    @Autowired ItemRepository itemRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired MockMvc mockMvc;

    @DisplayName("좋아요 추가 테스트")
    @Test
    @WithMockUser(roles = "USER")
    @Rollback
    public void addLikeTest() throws Exception {

        Item item = createItem();

        mockMvc.perform(post("/likes/"+item.getId()))
                .andExpect(status().isOk());

//        Like like = likeRepository.findAll().get(0);
//
//        assertNotNull(like);
//        assertNotNull(like.getItem().getId());
//        assertNotNull(like.getMember().getEmail());

    }

    private Item createItem() {
        Item item = Item.builder()
                .title("test")
                .sellPrice(10000)
                .status(Status.NEW)
                .member(memberRepository.findAll().get(0))
                .build();

        itemRepository.save(item);

        return item;
    }

}