package com.sh.app.movie.repository;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByNormalizedTitle(String normalizedTitle);

    @Query("SELECT m FROM Movie m JOIN m.movieGenres g WHERE g.genre.genreName = :genre")
    List<Movie> findByGenreName(String genre);

    List<Movie> findAllByOrderByTitleAsc();

    List<Movie> findAllByOrderByRankAsc();

    List<Movie> findFirst6ByOrderByRankAsc();
//    @Query(value = """
//         SELECT
//             m.id,
//             m.title,
//             m.film_ratings,
//             m.release_date,
//             m.running_time,
//             m.trailer,
//             m.poster,
//             m.director,
//             m.actor,
//             m.summary,
//             m.advance_reservation,
//             AVG(r.review_score) AS average_score
//         FROM
//             movieData m
//         JOIN
//             review r ON m.id = r.movie_id
//         GROUP BY
//             m.id,
//             m.title,
//             m.film_ratings,
//             m.release_date,
//             m.running_time,
//             m.trailer,
//             m.poster,
//             m.director,
//             m.actor,
//             m.summary,
//             m.advance_reservation
//         HAVING
//             AVG(r.review_score) >= (SELECT AVG(review_score)
//                                    FROM review
//                                    WHERE movie_id IN (SELECT id
//                                                       FROM movieData
//                                                       WHERE title LIKE %:title%))
//         ORDER BY
//                 CASE WHEN m.title LIKE %:title% THEN 0 ELSE 1 END,
//                    average_score asc""", nativeQuery = true)
//    List<Movie> findByTitleContaining(String search);
@Query(value = """
SELECT 
    m.*
FROM 
    movie m
WHERE ABS(m.vote_average - (SELECT AVG(m2.vote_average)
                            FROM movie m2
                            WHERE m2.title LIKE %:title%)) <= 1
ORDER BY CASE WHEN m.title LIKE %:title% THEN 0 ELSE 1 END ASC, -- 'title'를 포함하는 영화 우선
         ABS(m.vote_average - (SELECT AVG(m3.vote_average) 
                               FROM movie m3 
                               WHERE m3.title LIKE %:title%)) ASC, -- 'title' 평점과 가까운 순
         m.vote_average DESC -- 동일 평점 내에서는 높은 평점 우선
FETCH FIRST 10 ROWS ONLY""", nativeQuery = true)
    List<Movie> findByTitleContaining(String title);

}
