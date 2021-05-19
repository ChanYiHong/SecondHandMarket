package hcy.secondhandmarket.service.review;

import hcy.secondhandmarket.domain.Item;
import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Review;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewModifyDTO;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import hcy.secondhandmarket.dto.review.ReviewSaveDTO;
import hcy.secondhandmarket.repository.item.ItemRepository;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Long save(ReviewSaveDTO reviewSaveDTO, String email) {

        // Member
        Optional<Member> memberOptional = memberRepository.findByEmail(email, false);
        if(memberOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no member email : " + email);
        }
        Member member = memberOptional.get();

        // Item
        Optional<Item> itemOptional = itemRepository.findById(reviewSaveDTO.getItemId());
        if(itemOptional.isEmpty()) {
            throw new IllegalArgumentException("There is no item id : " + reviewSaveDTO.getItemId());
        }
        Item item = itemOptional.get();

        Review review = dtoToEntity(reviewSaveDTO, member, item);

        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    public PageResponseDTO<Object[], ReviewResponseDTO> getList(PageRequestDTO pageRequestDTO, Long itemId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> result = reviewRepository.findReviewByItemId(itemId, pageable);

        Function<Object[], ReviewResponseDTO> fn = content -> {
            return entityToDTO((Review) content[0], (Member) content[1]);
        };

        return new PageResponseDTO<>(fn, result);

    }

    @Override
    public ReviewResponseDTO getOne(Long id) {

        List<Object[]> result = reviewRepository.findReviewByIdWithMember(id);

        Review review = (Review) result.get(0)[0];
        Member member = (Member) result.get(0)[1];

        return entityToDTO(review, member);

    }

    @Override
    @Transactional
    public void modifyReview(ReviewModifyDTO reviewModifyDTO) {

        Optional<Review> optionalReview = reviewRepository.findById(reviewModifyDTO.getId());

        if(optionalReview.isEmpty()) {
            throw new IllegalArgumentException("There is no review id : " + reviewModifyDTO.getId());
        }

        Review review = optionalReview.get();

        review.changeContent(reviewModifyDTO.getContent());
        review.changeRating(reviewModifyDTO.getRating());

    }

    @Override
    @Transactional
    public void removeReview(Long id) {

        reviewRepository.deleteById(id);

    }
}
