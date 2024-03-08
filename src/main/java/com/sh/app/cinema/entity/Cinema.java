package com.sh.app.cinema.entity;

import com.sh.app.genre.entity.Genre;
import com.sh.app.location.entity.Location;
import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = {"theaters", "movies", "memberLikeCinemas"})
public class Cinema implements Comparable<Cinema>{

    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String region_cinema;
    @Column(nullable = false)
    private int theater_number;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Double location_lo; // 지도 경도
    @Column(nullable = false)
    private Double location_la; // 지도 위도
    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

//    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Theater> theaters = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
//    private List<MemberLikeCinema> memberLikeCinemas = new ArrayList<>();


    public void setLocation(Location location) {
        this.location = location;

        if(location != null) {
            if(!location.getCinemas().contains(this)) {
                location.getCinemas().add(this);
            }
        }
    }

    // 극장 브릿지 테이블
//    @ManyToMany
//    @JoinTable(
//            name = "movie_list",
//            joinColumns = @JoinColumn(name = "cinema_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id"))
//    @Builder.Default
//    private Set<Movie> movies = new LinkedHashSet<>();

    @Override
    public int compareTo(Cinema other) {
        return (int)(this.id - other.id);
    }
}
