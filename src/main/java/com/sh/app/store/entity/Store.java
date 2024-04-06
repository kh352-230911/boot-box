package com.sh.app.store.entity;

import com.sh.app.productBuy.entity.ProductBuy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_store_id_generator")
    @SequenceGenerator(
            name = "seq_store_id_generator",
            sequenceName = "seq_store_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String type;

    private String imageUrl;

    private String name;

    private Long price;

    private String description;

    private Integer expirationPeriod;
}
