//package com.sh.app.movieData.repository;
//
//import com.sh.app.movieData.entity.MovieData;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//
//public interface MovieDataRepository extends JpaRepository<MovieData, Long> {
//
//
//    //무비가져올때 이름정렬
//    List<MovieData> findAllByOrderByTitleAsc();
//
//
//
//    // nativeQuery
////    @Query(value = """
////    select
////        *
////    from(select
////            *
////        from
////            movieData
////        order by
////            advance_reservation desc)
////    where
////        rownum between 1 and 5
////    """, nativeQuery = true)
////    List<Movie> findFirst5ByOrderByAdvanceReservation();
//
//    // jpql
////    @Query("from Movie m order by m.advanceReservation desc limit 5")
//    // 쿼리 메소드
//    List<MovieData> findFirst5ByOrderByAdvanceReservationDesc();
//
//    @Query(value = """
// SELECT
//     m.id,
//     m.title,
//     m.film_ratings,
//     m.release_date,
//     m.running_time,
//     m.trailer,
//     m.poster,
//     m.director,
//     m.actor,
//     m.summary,
//     m.advance_reservation,
//     AVG(r.review_score) AS average_score
// FROM
//     movieData m
// JOIN
//     review r ON m.id = r.movie_id
// GROUP BY
//     m.id,
//     m.title,
//     m.film_ratings,
//     m.release_date,
//     m.running_time,
//     m.trailer,
//     m.poster,
//     m.director,
//     m.actor,
//     m.summary,
//     m.advance_reservation
// HAVING
//     AVG(r.review_score) >= (SELECT AVG(review_score)
//                            FROM review
//                            WHERE movie_id IN (SELECT id
//                                               FROM movieData
//                                               WHERE title LIKE %:title%))
// ORDER BY
//         CASE WHEN m.title LIKE %:title% THEN 0 ELSE 1 END,
//        average_score asc""", nativeQuery = true)
//    List<MovieData> findByTitleContaining(String title);
//
//    // 처음쿼리
////    @Query(value = """
////    SELECT
////        m.id,
////        m.title,
////        m.film_ratings,
////        m.release_date,
////        m.running_time,
////        m.trailer,
////        m.poster,
////        m.director,
////        m.actor,
////        m.summary,
////        m.advance_reservation,
////        AVG(r.review_score) AS average_score
////    FROM
////        movieData m
////    JOIN
////        review r ON m.id = r.movie_id
////    GROUP BY
////        m.id,
////        m.title,
////        m.film_ratings,
////        m.release_date,
////        m.running_time,
////        m.trailer,
////        m.poster,
////        m.director,
////        m.actor,
////        m.summary,
////        m.advance_reservation
////    HAVING
////        ABS(AVG(r.review_score) - (SELECT AVG(review_score)
////                                   FROM review
////                                   WHERE movie_id in (SELECT id
////                                                     FROM movieData
////                                                     WHERE title like %:title%))) < 0.1
////     ORDER BY
////        CASE WHEN m.title LIKE %:title% THEN 0 ELSE 1 END,
////        ABS(AVG(r.review_score) - (SELECT AVG(review_score)
////                                   FROM review
////                                   WHERE movie_id IN (SELECT id
////                                                     FROM movieData
////                                                     WHERE title LIKE %:title%))) ASC
////
////""", nativeQuery = true)
////    List<Movie> findByTitleContaining(String title);
//
//
//    @Query("select m, g from MovieData m join fetch m.genres g where g.genreList = :genreList")
//    List<MovieData> findByGenreList(String genreList);
//
//}
