package com.sh.app.ask.dto;

import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.entity.AskType;
import com.sh.app.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateAskDto {
    @Id
    @GeneratedValue(generator = "seq_ask_id_generator")
    @SequenceGenerator(
            name = "seq_ask_id_generator",
            sequenceName = "seq_ask_id",
            allocationSize = 1
    )
    private Long id;
    private Long memberId; // 문의작성한 회원아이디
    @NotEmpty(message = "제목은 필수 입력 사항입니다.")
    private String askTitle; // 문의제목
    @NotEmpty(message = "내용은 필수 입력 사항입니다.")
    private String askDetail; // 문의내용
    @NotNull(message = "문의유형 필수 선택 사항입니다.")
    private AskType askType; // 문의유형
    private LocalDateTime createdAt;

}
