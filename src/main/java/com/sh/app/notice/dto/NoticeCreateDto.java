package com.sh.app.notice.dto;

import com.sh.app.notice.entity.NoticeType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NoticeCreateDto {
    @NotEmpty(message = "제목을 작성해주세요.")
    private String title;
    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;
    @NotEmpty(message = "유형을 선택해주세요.")
    private NoticeType noticeType;


}
