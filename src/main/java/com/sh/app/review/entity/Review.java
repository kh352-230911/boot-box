package com.sh.app.review.entity;

import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "movie")
public class Review {
    @Id
    @GeneratedValue(generator = "seq_review_id_generator")
    @SequenceGenerator(
            name = "seq_review_id_generator",
            sequenceName = "seq_review_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    @Column(nullable = false)
    private String reservationId; // fk-예약아이디
    @Column(nullable = false)
    private Long memberId; // fk-회원아이디
    @Column(nullable = false)
    private double reviewScore; // 리뷰평점
    @Column(nullable = false)
    private String reviewDetail; // 리뷰내용
    @Column(nullable = false)
    @CurrentTimestamp
    private LocalDateTime reviewCreatedAt; // 리뷰작성날짜

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
