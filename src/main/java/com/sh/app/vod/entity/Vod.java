package com.sh.app.vod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vod")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_vod_id_generator")
    @SequenceGenerator(
            name = "seq_vod_id_generator",
            sequenceName = "seq_vod_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String vodName;

    private String vodUrl;

    private String type;
}
