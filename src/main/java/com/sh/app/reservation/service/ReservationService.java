package com.sh.app.reservation.service;

import com.sh.app.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //기존 rollback처리 class단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    //이하 reservation db로 insert , update할 작업을 작성한다.

    //insert 시 , 예약을 시작했다는 의미(yet->pending)


    /**
     * update 시
     * 
     * 1.pending->confirm
     *  예약진행중->완료
     *
     * 2.pending->yet
     *  예약진행중->취소
     *  결제실패,혹은 예약 진행중 예약 페이지 이탈했을 경우 취소로 간주
     *
     * 3.confirm->yet
     *  예약 완료->취소
     *  사용자가 자의로 예약을 취소한 경우
     *
     *
     */


}
