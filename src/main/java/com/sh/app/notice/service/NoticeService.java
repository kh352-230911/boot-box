package com.sh.app.notice.service;

import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> findAll() {
        System.out.println("공지조회 service");
        return noticeRepository.findAll();
    }
}
