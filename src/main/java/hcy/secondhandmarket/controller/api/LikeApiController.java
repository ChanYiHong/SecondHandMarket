package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Slf4j
public class LikeApiController {

    private final LikeService likeService;

    /** 좋아요 버튼 눌렀을 때. 세션은 나중에 좀 더 정교하게.. (로그인 안되있는 사용자면 팝업뜨던가..) **/
    @PostMapping("/{itemId}")
    public ResponseEntity<Boolean> addOrDeleteLike(@PathVariable("itemId") Long itemId,
                                          @AuthenticationPrincipal MemberDTO memberDTO) {

        log.info("Item id : {}", itemId);
        log.info("MemberDTO : {}", memberDTO);

        boolean result = likeService.saveOrDeleteLike(memberDTO, itemId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    /** Item에 좋아요 개수가 몇 개인지 화면에 출력 **/
    @GetMapping("/{itemId}/count")
    public ResponseEntity<Long> getLikeNumber(@PathVariable("itemId") Long itemId) {
        Long result = likeService.getLikeNum(itemId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /** Item 화면 처음 나올 떄 좋아요가 눌려있는지 안 눌려있는지 확인..**/
    @GetMapping("/{itemId}")
    public ResponseEntity<Boolean> isLikeOrNot(@PathVariable("itemId") Long itemId,
                                               @AuthenticationPrincipal MemberDTO memberDTO) {
        boolean result = likeService.isLikeOrNot(memberDTO, itemId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
