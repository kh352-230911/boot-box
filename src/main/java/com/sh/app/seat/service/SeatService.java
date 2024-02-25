package com.sh.app.seat.service;

import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.seat.entity.Seat;
import com.sh.app.seat.entity.SeatDto;
import com.sh.app.seat.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    //넘겨받은 상영스케쥴 아이디로 예약된 좌석 조회해보기~
    public List<SeatDto> findSeatsByScheduleId(Long scheduleId) {
        List<Seat> seats = seatRepository.findSeatsByScheduleId(scheduleId);
        return seats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //entity to dto
    private SeatDto convertToDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setName( seat.getName());
      return seatDto;
    }
}
