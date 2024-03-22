package com.sh.app.actor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_actor_id_generator")
    @SequenceGenerator(
            name = "seq_actor_id_generator",
            sequenceName = "seq_actor_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private Long actorId;

    private String actorName;
}
