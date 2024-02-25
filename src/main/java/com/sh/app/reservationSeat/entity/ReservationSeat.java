package com.sh.app.reservationSeat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationSeat
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_reservation_seat_id_generator")
    @SequenceGenerator(
            name = "seq_reservation_seat_id_generator",
            sequenceName = "seq_reservation_seat_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    @Column(name = "res_id")
    private String resId;
    @Column(name = "seat_id")
    private long seatId;
}
