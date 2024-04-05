package com.sh.app.memberLikeGenre.entity;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.genre.entity.Genre;
import com.sh.app.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "memberLikeGenre")
public class MemberLikeGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_member_like_genre_id_generator")
    @SequenceGenerator(
            name = "seq_member_like_genre_id_generator",
            sequenceName = "seq_member_like_genre_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // member.member_id 컬럼지정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id") // genre.genre_id 컬럼지정
    private Genre genre;
}
