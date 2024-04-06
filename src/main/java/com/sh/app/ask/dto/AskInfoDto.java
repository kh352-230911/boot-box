package com.sh.app.ask.dto;

import com.sh.app.ask.entity.AskType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AskInfoDto {
    private Long id;

    private String askTitle;

    private String askDetail;

    private AskType askType;

    private LocalDateTime createdAt;
}
