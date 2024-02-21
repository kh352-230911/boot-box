package com.sh.app.notice.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
    @Column(nullable = false, name = "notice_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private NoticeType noticeType;
    @Column(nullable = false, name = "notice_title")
    private String noticeTitle;
    private String noticeContent;
}
