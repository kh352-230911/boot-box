package com.sh.app.answer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(generator = "seq_answer_id_generator")
    @SequenceGenerator(
            name = "seq_answer_id_generator",
            sequenceName = "seq_answer_id",
            initialValue = 1,
            allocationSize = 1
    )
    @Column
    private Long id;
    private String content; // 내용
    private LocalDate createdAt; // 날짜
}
