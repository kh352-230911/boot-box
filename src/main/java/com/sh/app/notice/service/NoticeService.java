package com.sh.app.notice.service;


import com.sh.app.notice.dto.NoticeCreateDto;
import com.sh.app.notice.dto.NoticeListDto;
import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<NoticeListDto> findAll() {
        return noticeRepository.findAll()
                .stream()
                .map((notice) -> convertToNoticeListDto(notice))
                .collect(Collectors.toList());
    }

    private NoticeListDto convertToNoticeListDto(Notice notice) {
        NoticeListDto noticeListDto = modelMapper.map(notice, NoticeListDto.class);
        return noticeListDto;
    }


    public NoticeListDto findById(Long id) {
        return noticeRepository.findById(id)
                .map((notice) -> convertToNoticeListDto(notice))
                .orElseThrow();
    }

    public void createNotice(NoticeCreateDto noticeCreateDto) {
        // noticeCreateDto를 Notice 엔티티로 변환
        Notice notice = convertToNotice(noticeCreateDto);

        // Notice 엔티티를 데이터베이스에 저장
        noticeRepository.save(notice);
    }

    private Notice convertToNotice(NoticeCreateDto noticeCreateDto) {
        return modelMapper.map(noticeCreateDto, Notice.class);
    }

    public void deleteById(Long id) {
        noticeRepository.deleteById(id);
    }
}
