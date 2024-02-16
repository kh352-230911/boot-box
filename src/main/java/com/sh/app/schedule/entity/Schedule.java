package com.sh.app.schedule.entity;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
>>>>>>> 8f64a24f04c405bc5818260f56409be0b51d862a

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
    private LocalDate schDate;
    @Column(nullable = false)
    private LocalDateTime time;
    
    public void setTheater(Theater theater) {
        this.theater = theater;

        if(theater != null) {
            if(theater.getSchedules().contains(this))
                theater.getSchedules().add(this);
        }
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        if(movie != null) {
            if(movie.getSchedules().contains(this))
                movie.getSchedules().add(this);
        }
    }

}
