package com.sh.app.ask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ask")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(nullable = false, name = "member_login_id")
    private String memberLoginId; // 문의작성한 회원아이디
    private String askTitle; // 문의제목
    private String askDetail; // 문의내용
    @Column(nullable = false, name = "ask_type")
    @Enumerated(EnumType.STRING)
    private AskType askType; // 문의유형
    private LocalDate createdAt;

}
