package com.sh.app.cinema.repository;

import com.sh.app.cinema.entity.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    @Query("from Cinema c where c.region_cinema like '%' || :name || '%'")
    List<Cinema> findByNameContaining(String name);

<<<<<<< HEAD
    List<Cinema> findByLocationId(Long locationId);
=======
    @Query("from Cinema c left join fetch c.location")
    Page<Cinema> findAll(Pageable pageable);
>>>>>>> 1b6b4f080b44550d25f13efbd053eefae71a70d8


}
