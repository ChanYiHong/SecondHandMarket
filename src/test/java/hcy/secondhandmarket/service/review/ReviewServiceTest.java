package hcy.secondhandmarket.service.review;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.Review;
import hcy.secondhandmarket.dto.review.ReviewResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Test
    public void findReviewByIdWithMemberTest() throws Exception {

        ReviewResponseDTO result = reviewService.getOne(1L);

        System.out.println(result);

    }

}