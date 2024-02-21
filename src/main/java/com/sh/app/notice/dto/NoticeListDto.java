package com.sh.app.notice.dto;

import com.sh.app.notice.entity.NoticeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NoticeListDto {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private NoticeType noticeType;
    private LocalDateTime createdAt;
}
