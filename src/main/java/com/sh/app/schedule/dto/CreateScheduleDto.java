package com.sh.app.schedule.dto;

import com.sh.app.ask.entity.AskType;
import com.sh.app.common.Approve;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateScheduleDto {
    @Id
    @GeneratedValue(generator = "seq_schedule_id_generator")
    @SequenceGenerator(
            name = "seq_schedule_id_generator",
            sequenceName = "seq_schedule_id",
            allocationSize = 1
    )
    private Long id;
    private long theaterId;
    private long movieId;
    private LocalDate schDate;
    private LocalDateTime time;
    private Approve approve;

}
