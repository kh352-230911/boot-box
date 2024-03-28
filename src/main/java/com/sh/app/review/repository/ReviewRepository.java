package com.sh.app.review.repository;

import com.sh.app.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewCreatedAtBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);

    long countByMovieId(Long movieId);

    Optional<Review> findByReservationId(String id);

    List<Review> findByMemberId(Long id);
//    @Query("SELECT AVG(r.reviewScore) FROM Review r WHERE r.movie.id = :id")
//    Double getAverageRatingByMovieId(Long id);
}
