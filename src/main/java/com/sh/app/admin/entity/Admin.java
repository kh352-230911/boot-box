package com.sh.app.admin.entity;

import com.sh.app.authority.entity.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Admin implements Serializable {
    @Id
    @GeneratedValue(generator = "seq_admin_id_generator")
    @SequenceGenerator(
            name = "seq_admin_id_generator",
            sequenceName = "seq_admin_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;
    private Long cinemaId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id") // authority.member_id 컬럼 작성
    private List<Authority> authorities;
}
