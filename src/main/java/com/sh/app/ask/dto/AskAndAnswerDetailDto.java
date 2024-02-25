package com.sh.app.ask.dto;

import com.sh.app.answer.dto.AnswerDetailDto;
import lombok.Data;

@Data
public class AskAndAnswerDetailDto {
    private AnswerDetailDto answerDetailDto;
    private AskDetailDto askDetailDto;
}
