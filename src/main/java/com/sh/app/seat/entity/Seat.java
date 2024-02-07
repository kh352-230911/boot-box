package com.sh.app.seat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat implements Comparable<Seat>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public int compareTo(Seat other) {
        return (int)(this.id - other.id);
    }
}
