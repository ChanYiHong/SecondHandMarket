package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Slf4j
public class LikeApiController {

    private final LikeService likeService;

    /** 좋아요 버튼 눌렀을 때. 세션은 나중에 좀 더 정교하게.. (로그인 안되있는 사용자면 팝업뜨던가..) **/
    @PostMapping("/{itemId}")
    public ResponseEntity<String> addLike(@PathVariable("itemId") Long itemId,
                                          @AuthenticationPrincipal MemberDTO memberDTO) {

        log.info("Item id : {}", itemId);
        log.info("MemberDTO : {}", memberDTO);

        boolean result = false;

        if(memberDTO != null) {
            result = likeService.saveLike(memberDTO, itemId);
        }

        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
