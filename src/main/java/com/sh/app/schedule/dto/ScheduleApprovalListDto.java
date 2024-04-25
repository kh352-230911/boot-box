package com.sh.app.schedule.dto;

import com.sh.app.common.Approve;
import com.sh.app.movie.dto.MovieInfoDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.theater.dto.TheaterInfoDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleApprovalListDto {
    private Long id;

    private TheaterInfoDto theaterInfoDto;

    private MovieInfoDto movieInfoDto;

    private LocalDate schDate;

    private LocalDateTime time;

    private Approve approve;
}
