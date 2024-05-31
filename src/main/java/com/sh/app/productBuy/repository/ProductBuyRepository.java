package com.sh.app.productBuy.repository;

import com.sh.app.productBuy.entity.ProductBuy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBuyRepository extends JpaRepository<ProductBuy, Long> {
}
