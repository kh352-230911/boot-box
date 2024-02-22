package com.sh.app.notice.dto;

import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.notice.entity.NoticeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NoticeCreateDto {
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
    @NotEmpty(message = "제목을 작성해주세요.")
    private String title;
    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;
    @NotNull
    private NoticeType noticeType;
}
