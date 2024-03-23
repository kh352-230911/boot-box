package com.sh.app.vod.entity;

import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vod")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "movie")
public class Vod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_vod_id_generator")
    @SequenceGenerator(
            name = "seq_vod_id_generator",
            sequenceName = "seq_vod_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String vodName;

    private String vodUrl;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
