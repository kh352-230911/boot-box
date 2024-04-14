package com.sh.app.pay.repository;

import com.sh.app.pay.entity.OrderPay;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//주문내역테이블
public interface OrderPayRepository extends JpaRepository<OrderPay,String>
{

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM order_pay WHERE reservation_id = :reservationId", nativeQuery = true)
    void deleteByReservationId(@Param("reservationId") String reservationId);

    //reservation id로 특정 imp 조회
    OrderPay findByReservationId(String reservationId);
}
