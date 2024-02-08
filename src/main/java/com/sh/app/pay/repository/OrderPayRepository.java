package com.sh.app.pay.repository;

import com.sh.app.pay.entity.OrderPay;
import org.springframework.data.jpa.repository.JpaRepository;

//주문내역테이블
public interface OrderPayRepository extends JpaRepository<OrderPay,String>
{

}
