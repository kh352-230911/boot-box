package com.sh.app.theater.entity;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.movie.entity.Movie;
import com.sh.app.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "schedules")
public class Theater {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int seat;

    @OneToMany(mappedBy = "theater", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Schedule> schedules = new ArrayList<>();

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;

        if(cinema != null) {
            if(cinema.getTheaters().contains(this))
                cinema.getTheaters().add(this);
        }
    }
}
