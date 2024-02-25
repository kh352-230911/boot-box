package com.sh.app.answer.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnswerDetailDto {
    private Long id;
    private Long askId;
    private Long adminId;
    private String content;
    private LocalDate createdAt;
}
