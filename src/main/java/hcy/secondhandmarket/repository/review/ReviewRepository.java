package hcy.secondhandmarket.repository.review;

import hcy.secondhandmarket.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r, m from Review r left join r.member m left join r.item i where i.id = :itemId")
    Page<Object[]> findReviewByItemId(@Param("itemId") Long itemId, Pageable pageable);

    @Query("select r, m from Review r left join r.member m where r.id = :id")
    List<Object[]> findReviewByIdWithMember(@Param("id") Long id);

    @Query("select count(r) from Review r left join r.member m where m.email = :email")
    Long findReviewCntByEmail(@Param("email") String email);

    @Query("select r, m from Review r left join r.member m where m.email = :email")
    Page<Object[]> findReviewByMemberEmail(@Param("email") String email, Pageable pageable);

}
