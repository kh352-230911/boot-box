package com.sh.app.memberLikeCinema.entity;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "memberLikeCinema")
@ToString(exclude = {"member", "cinema"})
public class MemberLikeCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_member_like_cinema_id_generator")
    @SequenceGenerator(
            name = "seq_member_like_cinema_id_generator",
            sequenceName = "seq_member_like_cinema_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // member.member_id 컬럼지정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id") // cinema.cinema_id 컬럼지정
    private Cinema cinema;
}
