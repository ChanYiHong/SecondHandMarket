package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewModifyDTO;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewSaveDTO;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    // 댓글 리스트. (item id로)
    @GetMapping(value = "/list/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponseDTO<Object[], ReviewResponseDTO>> getReviewList(
            @PathVariable("itemId") Long itemId, @ModelAttribute PageRequestDTO pageRequestDTO) {

        log.info("Get reviews Item id : {}", itemId);

        PageResponseDTO<Object[], ReviewResponseDTO> result = reviewService.getList(pageRequestDTO, itemId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // 댓글 1개만.
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReviewResponseDTO> getReview(@PathVariable("id") Long id) {

        log.info("Get review id : {}", id);

        ReviewResponseDTO result = reviewService.getOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // 댓글 등록
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> createReview(@RequestBody ReviewSaveDTO reviewSaveDTO, @AuthenticationPrincipal MemberDTO memberDTO) {

        log.info("Create Review : {}", reviewSaveDTO);

        Long id = reviewService.save(reviewSaveDTO, memberDTO.getEmail());

        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    // 댓글 수정.
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Long> modifyReview(@RequestBody ReviewModifyDTO reviewModifyDTO) {

        log.info("Modify Review : {}", reviewModifyDTO);

        reviewService.modifyReview(reviewModifyDTO);

        return new ResponseEntity<>(reviewModifyDTO.getId(), HttpStatus.OK);

    }

    // 댓글 삭제.
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Long> removeReview(@PathVariable("id") Long id) {

        log.info("Remove Review : {}", id);

        reviewService.removeReview(id);

        return new ResponseEntity<>(id, HttpStatus.OK);

    }



}
