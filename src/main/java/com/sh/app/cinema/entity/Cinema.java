package com.sh.app.cinema.entity;

import com.sh.app.location.entity.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cinema implements Comparable<Cinema>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String region_cinema;
    @Column(nullable = false)
    private int theater_number;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int location_lo; // 지도 경도
    @Column(nullable = false)
    private int location_la; // 지도 위도
    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

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
