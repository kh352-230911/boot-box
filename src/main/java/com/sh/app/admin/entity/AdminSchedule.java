package com.sh.app.admin.entity;


import com.sh.app.common.Approve;
import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;

//0423 지점관리자 - 상영스케쥴 추가
@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) //0219 eager에서 수정
    @JoinColumn(name = "theater_id")
    @BatchSize(size = 50)
    private Theater theater;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //0219 eager에서 수정
    @JoinColumn(name = "movie_id")
    @BatchSize(size = 50)
    private Movie movie;
    @Column(nullable = false)
    private LocalDate schDate;
    @Column(nullable = false)
    private LocalDateTime time;
    //approve 컬럼 신규추가(승인 된 경우 y , 보류인 경우 n 기본값은 n으로 지정할 예정)
    @Column
    private Approve approve;

}
