package com.sh.app.location.entity;

import com.sh.app.cinema.entity.Cinema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = "cinemas") // ToString 무한루프 방지
public class Location {

    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String location_name;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Cinema> cinemas = new ArrayList<>();
}
