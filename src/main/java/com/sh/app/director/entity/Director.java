package com.sh.app.director.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "director")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_director_id_generator")
    @SequenceGenerator(
            name = "seq_director_id_generator",
            sequenceName = "seq_director_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private Long directorId;

    private String directorName;
}
