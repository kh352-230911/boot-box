package com.sh.app.notice.repository;

import com.sh.app.notice.entity.Notice;

import java.util.List;

public interface NoticeRepository {
    List<Notice> findAll();
}
