package hcy.secondhandmarket.service.review;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Review;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewModifyDTO;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewSaveDTO;

import java.util.List;

public interface ReviewService {

    Long save(ReviewSaveDTO reviewSaveDTO, String email);

    PageResponseDTO<Object[], ReviewResponseDTO> getList(PageRequestDTO pageRequestDTO, Long itemId);

    ReviewResponseDTO getOne(Long id);

    void modifyReview(ReviewModifyDTO reviewModifyDTO);

    void removeReview(Long id);

    default Review dtoToEntity(ReviewSaveDTO reviewSaveDTO, Member member, Item item) {

        return Review.builder()
                .content(reviewSaveDTO.getContent())
                .rating(reviewSaveDTO.getRating())
                .member(member)
                .item(item)
                .build();
    }

    default ReviewResponseDTO entityToDTO(Review review, Member member) {

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .writer(member.getName())
                .build();

    }

}
