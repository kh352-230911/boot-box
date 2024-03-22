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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vodName;

    private String vodUrl;

    private String type;
}
