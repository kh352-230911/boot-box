package com.sh.app.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(generator = "seq_reservation_id_generator")
    @SequenceGenerator(
            name = "seq_reservation_id_generator",
            sequenceName = "seq_reservation_id",
            // 첫번째 할당할값, 몇개할당할까
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private Long scheduleId;
    @Column(nullable = false)
    private String status;
}
