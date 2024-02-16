package com.sh.app.authority.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "admin_id")
    private Long adminId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleAuth name;
}
