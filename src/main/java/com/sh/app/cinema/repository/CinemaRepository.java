package com.sh.app.cinema.repository;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    @Query("from Cinema c where c.region_cinema like '%' || :name || '%'")
    List<Cinema> findByNameContaining(String name);

    @Query("from Cinema c left join fetch c.location")
    Page<Cinema> findAll(Pageable pageable);

    @Query("SELECT c.movies FROM Cinema c WHERE c.id = :id")
    List<Movie> findMoviesByCinemaId(Long id);
}
