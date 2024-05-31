package com.sh.app.answer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnswerCreateDto {
    private Long id;
    private Long askId; // 해당 답변이 연결된 문의의 ID
    private Long adminId;
    @NotEmpty(message = "내용은 필수 입력 사항입니다.")
    private String content; // 답변 내용
    private LocalDateTime createdAt;
}
