package com.sh.app.ask.repository;

import com.sh.app.ask.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask, Long> {
    List<Ask> findAll();
}
