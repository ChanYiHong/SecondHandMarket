package hcy.secondhandmarket.service.like;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Like;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.like.LikeRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /** 세션 사용자 정보 + 현재 Pathvariable로 받은 itemId로 좋아요를 찾고, 처음 좋아요면 추가 아니면 false 반환. **/
    public boolean saveLike(MemberDTO memberDTO, Long itemId) {

        Member member = memberRepository.findByEmail(memberDTO.getEmail(), memberDTO.isFromSocial()).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        if(isFirstLike(member, item)) {
            likeRepository.save(new Like(member, item));
            return true;
        }

        return false;

    }

    /** Item에 좋아요 개수 찾기. **/
    public Long getLikeNum(Long itemId) {

        log.info("Item id : {} 의 좋아요 개수 찾기", itemId);

        Long result = likeRepository.findNumberOfLikeByItemId(itemId);

        log.info("Item id : {} 의 좋아요 개수 : {}", itemId, result);

        return result;

    }

    private boolean isFirstLike(Member member, Item item) {
        return likeRepository.findByItemAndMember(item, member).isEmpty();
    }

}
