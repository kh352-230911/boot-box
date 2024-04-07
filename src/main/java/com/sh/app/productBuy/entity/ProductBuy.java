package com.sh.app.productBuy.entity;

import com.sh.app.productOrderPay.entity.ProductOrderPay;
import com.sh.app.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_buy")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_product_buy_id_generator")
    @SequenceGenerator(
            name = "seq_product_buy_id_generator",
            sequenceName = "seq_product_buy_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private ProductOrderPay productOrderPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
