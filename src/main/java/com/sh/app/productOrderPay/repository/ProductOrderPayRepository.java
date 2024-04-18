package com.sh.app.productOrderPay.repository;

import com.sh.app.productOrderPay.entity.ProductOrderPay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderPayRepository extends JpaRepository<ProductOrderPay, String> {
}
