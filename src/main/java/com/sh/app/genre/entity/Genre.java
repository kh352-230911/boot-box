package com.sh.app.genre.entity;

import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "memberLikeGenres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genre_id_generator")
    @SequenceGenerator(
            name = "seq_genre_id_generator",
            sequenceName = "seq_genre_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private Long genreId;

    private String genreName;


    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberLikeGenre> memberLikeGenres;


}
