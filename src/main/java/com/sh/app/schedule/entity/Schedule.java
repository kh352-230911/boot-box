package com.sh.app.schedule.entity;

import com.sh.app.movie.entity.Movie;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "reservations"})
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) //0219 eager에서 수정
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @ManyToOne(fetch = FetchType.LAZY) //0219 eager에서 수정
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(nullable = false)
    private LocalDate schDate;
    @Column(nullable = false)
    private LocalDateTime time;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Reservation> reservations = new HashSet<>();
    
//    public void setTheater(Theater theater) {
//        this.theater = theater;
//
//        if(theater != null) {
//            if(theater.getSchedules().contains(this))
//                theater.getSchedules().add(this);
//        }
//    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        if(movie != null) {
            if(movie.getSchedules().contains(this))
                movie.getSchedules().add(this);
        }
    }

}
