package com.sh.app.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "notice",
        uniqueConstraints =
        @UniqueConstraint(
                name = "uq_notice_name",
                columnNames = {"notice_title"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {
    @Id
    @GeneratedValue(generator = "seq_notice_id_generator")
    @SequenceGenerator(
            name = "seq_notice_id_generator",
            sequenceName = "seq_notice_id",
            initialValue = 1,
            allocationSize = 1
    )
    //  <!-- 시퀀스id, noticeType, title, content, createAt -->
    private Long id;
    private Long adminId; // notice table의 시퀀스
    @Column(nullable = false, name = "notice_title")
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "notice_type")
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;
}
