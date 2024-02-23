package com.sh.app.ask.repository;

import com.sh.app.ask.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask, Long> {
    @Query("from Ask order by id")
    List<Ask> findAll();
}
