package com.sh.app.productOrderPay.entity;

import com.sh.app.productBuy.entity.ProductBuy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_order_pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderPay {
    @Id
    private String id;

    @Column(name = "member_id")
    private Long memberId;

    private String amount;

    private Long price;

    private Integer count;

    private LocalDateTime payTime;

    private LocalDateTime cancelPayTime;

    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    @OneToMany(mappedBy = "productOrderPay", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 50)
    private List<ProductBuy> productBuys = new ArrayList<>();
}
