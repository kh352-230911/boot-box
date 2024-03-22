package com.sh.app.ask.entity;

import com.sh.app.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ask")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "member")
public class Ask implements Serializable {
    @Id
    @GeneratedValue(generator = "seq_ask_id_generator")
    @SequenceGenerator(
            name = "seq_ask_id_generator",
            sequenceName = "seq_ask_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
//    private Long memberId;
    private String askTitle; // 문의제목
    private String askDetail; // 문의내용
    @Column(nullable = false, name = "ask_type")
    @Enumerated(EnumType.STRING)
    private AskType askType; // 문의유형
    @CurrentTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // member_id 컬럼지정
    private Member member;

}