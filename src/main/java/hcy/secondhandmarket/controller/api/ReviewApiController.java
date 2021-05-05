package hcy.secondhandmarket.controller.api;

import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import hcy.secondhandmarket.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PageResponseDTO<Object[], ReviewResponseDTO>> getReviewList(
            @PathVariable("itemId") Long itemId, @ModelAttribute PageRequestDTO pageRequestDTO) {

        log.info("Get reviews Item id : {}", itemId);

        PageResponseDTO<Object[], ReviewResponseDTO> result = reviewService.getList(pageRequestDTO, itemId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }



}
