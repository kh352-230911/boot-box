package com.sh.app.schedule.entity;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(nullable = false)
    private String schDate;
    @Column(nullable = false)
    private String time;
    
    public void setTheater(Theater theater) {
        this.theater = theater;

        if(theater != null) {
            if(theater.getSchedules().contains(this))
                theater.getSchedules().add(this);
        }
    }

}
