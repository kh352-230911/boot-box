package com.sh.app.reservation.service;

import com.sh.app.authority.entity.Authority;
import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.member.entity.Member;
import com.sh.app.reservation.dto.ReservationDto;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.reservation.repository.ReservationRepository;
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
    private ModelMapper modelMapper;


    //응답결과를 db에 저장하는 메소드.
    public Reservation insertReservation(ReservationDto reservationDto)
    {
       return reservationRepository.save(convertDtoToEntity(reservationDto));

    }

    //modelmapper이용해서 변환하기
    
    

    //dto -> entity
    public Reservation convertDtoToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

    //entity ->dto
    public ReservationDto convertEntityToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }





}
