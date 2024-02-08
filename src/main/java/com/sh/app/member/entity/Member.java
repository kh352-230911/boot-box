package com.sh.app.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
