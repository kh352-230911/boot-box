package com.sh.app.ask.dto;

import com.sh.app.ask.entity.AskType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AskDetailDto {
    private Long id;
    private String memberId;
    private String askTitle;
    private String askDetail;
    private AskType askType;
    private LocalDate createdAt;
}
