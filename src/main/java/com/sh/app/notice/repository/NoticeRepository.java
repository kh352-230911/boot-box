package com.sh.app.notice.repository;

import com.sh.app.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("from Notice order by id asc")
    List<Notice> findAll();
}
