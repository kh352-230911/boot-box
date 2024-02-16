package com.sh.app.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notice")
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
    @Column
    private Long id;
    private Long adminId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeType;

}
