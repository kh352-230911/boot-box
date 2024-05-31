package com.sh.app.movieActor.entity;

import com.sh.app.actor.entity.Actor;
import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie_actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "actor"})
@EqualsAndHashCode(of = "id")
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_movie_actor_id_generator")
    @SequenceGenerator(
            name = "seq_movie_actor_id_generator",
            sequenceName = "seq_movie_actor_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
