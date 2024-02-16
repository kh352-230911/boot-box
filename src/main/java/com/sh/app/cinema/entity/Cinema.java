package com.sh.app.cinema.entity;

import com.sh.app.location.entity.Location;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "theaters")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Theater> theaters = new ArrayList<>();

    public void setLocation(Location location) {
        this.location = location;

        if(location != null) {
            if(!location.getCinemas().contains(this)) {
                location.getCinemas().add(this);
            }
        }
    }

    @Override
    public int compareTo(Cinema other) {
        return (int)(this.id - other.id);
    }
}
