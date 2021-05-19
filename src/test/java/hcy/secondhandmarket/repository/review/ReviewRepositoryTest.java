package hcy.secondhandmarket.repository.review;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void findReviewCntByEmailTest() throws Exception {

        Long result = reviewRepository.findReviewCntByEmail("user1@hcy.com");

        System.out.println(result);

    }

    @Test
    @Transactional
    public void findReviewByIdWithMemberTest() throws Exception {

        List<Object[]> result = reviewRepository.findReviewByIdWithMember(1L);

        System.out.println(result.size());

        Review review = (Review) result.get(0)[0];
        Member member = (Member) result.get(0)[1];
        System.out.println(review);
        System.out.println(member);

    }

}