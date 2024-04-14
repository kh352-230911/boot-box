package com.sh.app.reservation.service;

import com.sh.app.authority.entity.Authority;
import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.member.entity.Member;
import com.sh.app.pay.dto.OrderPayDto;
import com.sh.app.pay.entity.OrderPay;
import com.sh.app.pay.repository.OrderPayRepository;
import com.sh.app.reservation.dto.ReservationDto;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.reservation.repository.ReservationRepository;
import com.sh.app.reservationSeat.dto.ReservationSeatDto;
import com.sh.app.reservationSeat.dto.ReservationSeatDto2;
import com.sh.app.reservationSeat.entity.ReservationSeat;
import com.sh.app.reservationSeat.repository.ReservationSeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 */
@Service
@Transactional //기존 rollback처리 class단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationSeatRepository reservationSeatRepository;

    @Autowired
    OrderPayRepository orderPayRepository;

    @Autowired
    private ModelMapper modelMapper;


    //응답결과를 db에 저장하는 메소드.
    public Reservation insertReservation(ReservationDto reservationDto)
    {
       return reservationRepository.save(convertDtoToEntity(reservationDto));
    }

    //modelmapper이용해서 변환하기

    //1)reservation
    //dto -> entity
    public Reservation convertDtoToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }
    //entity ->dto
    public ReservationDto convertEntityToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    //2)reservationSeat
    //dto -> entity
    public ReservationSeat convertDtoToEntity(ReservationSeatDto2 reservationSeatDto2) {
        return modelMapper.map(reservationSeatDto2, ReservationSeat.class);
    }
    //entity ->dto
    public ReservationSeatDto2 convertEntityToDto(ReservationSeat reservationSeat) {
        return modelMapper.map(reservationSeat, ReservationSeatDto2.class);
    }

    //3)orderPay
    //dto -> entity
    public OrderPay convertDtoToEntity(OrderPayDto orderPayDto) {
        return modelMapper.map(orderPayDto, OrderPay.class);
    }
    //entity ->dto
    public OrderPayDto convertEntityToDto(OrderPay orderPay) {
        return modelMapper.map(orderPay, OrderPayDto.class);
    }


    public ReservationSeatDto2 insertReservationSeat(ReservationSeatDto2 reservationSeatDto2) {

        return convertEntityToDto(reservationSeatRepository.save(convertDtoToEntity(reservationSeatDto2)));
    }

    public OrderPayDto insertOrderPay(OrderPayDto orderPayDto) {
        return convertEntityToDto(orderPayRepository.save(convertDtoToEntity(orderPayDto)));
    }


    //예매를 취소하는 메소드
    public boolean cancelReservation(String reservationId) {
        try {
            orderPayRepository.deleteByReservationId(reservationId);
            reservationSeatRepository.deleteByReservationId(reservationId);
            reservationRepository.deleteById(reservationId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
