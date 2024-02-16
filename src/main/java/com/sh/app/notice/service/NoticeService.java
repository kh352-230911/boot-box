package com.sh.app.notice.service;

import com.sh.app.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

}
