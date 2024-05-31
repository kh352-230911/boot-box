package com.sh.app.admin.repository;

import com.sh.app.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("from Admin a join fetch a.authorities where a.username = :username")
    Admin findByUsername(String username);

//    @Query("select a from Admin a join fetch a.cinemaId")
//    List<Admin> findAllWithCinemaId();
}
