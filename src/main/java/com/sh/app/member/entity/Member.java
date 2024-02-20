package com.sh.app.member.entity;

import com.sh.app.ask.entity.Ask;
import com.sh.app.authority.entity.Authority;
import com.sh.app.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 0206 member entity - db member table
 */

@Entity //테이블의 id를 설정해야 오류가 나지 않는다~
@Table(name="member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert //null이 아닌 필드값만 insert한다.
@DynamicUpdate //영속성 컨텍스트의 엔티티와 달라진 필드만 update한다.
@ToString(exclude = "reviews")
public class Member implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_member_id_generator")
    @SequenceGenerator(
            name = "seq_member_id_generator",
            sequenceName = "seq_member_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @Column(nullable =false, unique = true)
    private String memberLoginId	; //uq

    @Column(nullable = false)
    private String memberPwd;

    @Column(nullable =false, unique = true)
    private String memberEmail; //uq

    @Column(nullable = false)
    private String memberName;

    @Column(nullable =false, unique = true)
    private String memberPhone;

    private String birthyear; //null ok

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // authority.member_id 컬럼 작성
    private List<Authority> authorities;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Ask> asks = new ArrayList<>();


}
