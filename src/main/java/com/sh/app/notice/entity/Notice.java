package com.sh.app.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class Notice implements Serializable {
    @Id
    @GeneratedValue(generator = "seq_notice_id_generator")
    @SequenceGenerator(
            name = "seq_notice_id_generator",
            sequenceName = "seq_notice_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private Long adminId;
    @Column(nullable = false, name = "notice_title")
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "notice_type")
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;
}
