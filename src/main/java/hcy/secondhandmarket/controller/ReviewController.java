package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import hcy.secondhandmarket.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/info/{email}")
    public String getReviewListByEmail(@PathVariable("email") String email, @ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

        log.info("Get review list By Email : {}, PageRequestDTO : {}", email, pageRequestDTO);

        PageResponseDTO<Object[], ReviewResponseDTO> result = reviewService.getListByEmail(pageRequestDTO, email);

        model.addAttribute("result", result);

        return "/reviews/list";

    }

}
