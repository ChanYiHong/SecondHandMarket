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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /** 세션 사용자 정보 + 현재 Pathvariable로 받은 itemId로 좋아요를 찾고, 처음 좋아요면 추가 아니면 false 반환. **/
    @Transactional
    public boolean saveOrDeleteLike(MemberDTO memberDTO, Long itemId) {

        Member member = memberRepository.findByEmail(memberDTO.getEmail(), memberDTO.isFromSocial()).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        // 해당 유져와, 아이템에 관해서 좋아요를 찾음.
        Optional<Like> result = likeRepository.findByItemAndMember(item, member);
        // 좋아요를 누른적이 없다면, 추가
        if(result.isEmpty()) {
            likeRepository.save(new Like(member, item));
            return true;
        }
        // 좋아요가 이미 눌려져 있었다면, 삭제
        else {
            Like like = result.orElseThrow();
            likeRepository.delete(like);
            return false;
        }

    }

    /** Item에 좋아요 개수 찾기. **/
    public Long getLikeNum(Long itemId) {

        log.info("Item id : {} 의 좋아요 개수 찾기", itemId);

        Long result = likeRepository.findNumberOfLikeByItemId(itemId);

        log.info("Item id : {} 의 좋아요 개수 : {}", itemId, result);

        return result;

    }

    /** 좋아요 색깔 정보를 위해 해당 사용자가 눌렀는지 안눌렀는지만.. **/
    public boolean isLikeOrNot(MemberDTO memberDTO, Long itemId) {
        Member member = memberRepository.findByEmail(memberDTO.getEmail(), memberDTO.isFromSocial()).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();
        return !isFirstLike(member, item);
    }

    private boolean isFirstLike(Member member, Item item) {
        return likeRepository.findByItemAndMember(item, member).isEmpty();
    }

}
