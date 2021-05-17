package hcy.secondhandmarket.repository.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}