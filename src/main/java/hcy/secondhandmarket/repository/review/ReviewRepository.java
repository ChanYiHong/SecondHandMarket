package hcy.secondhandmarket.repository.review;

import hcy.secondhandmarket.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r, m from Review r left join r.member m left join r.item i where i.id = :itemId")
    Page<Object[]> findReviewByItemId(@Param("itemId") Long itemId, Pageable pageable);

}
