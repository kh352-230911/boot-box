package com.sh.app.pay.repository;

import com.sh.app.pay.entity.CancelPay;
import com.sh.app.pay.entity.OrderPay;
import org.springframework.data.jpa.repository.JpaRepository;

//환불테이블
public interface CanclePayRepository extends JpaRepository<CancelPay,Long>
{

}
