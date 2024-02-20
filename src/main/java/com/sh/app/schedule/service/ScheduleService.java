package com.sh.app.schedule.service;


import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.repository.ScheduleRepository;
import com.sh.app.theater.dto.TheaterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


/**
 * schedule이 단독으로 쓰일일은 거의 없지만 현재 db에 저장한 시험데이터를 가져오기 위해 만들음.
 */
@Service
@Transactional //기존 rollback처리 class단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨

public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    /*
        modelMapper
        두 개의 서로 다른 클래스나 데이터 모델 간에 필드 값을 복사하거나 이동하는 작업을 의미합니다.
        DTO(Data Transfer Object)와 엔티티(Entity) 사이의 변환,
        또는 여러 서로 다른 데이터 모델 간의 상호 작용을 처리할 때 특히 유용
     */
    @Autowired
    private ModelMapper modelMapper; // ModelMapper를 사용하여 엔티티를 DTO로 변환.

    //0217 - 모든 상영 스케쥴 출력[test]
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    //0219 -
    public List<ScheduleDto> findAllSchedulesDto() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ScheduleDto convertToDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setSchDate(schedule.getSchDate());
        scheduleDto.setTime(schedule.getTime());

        // TheaterDto 생성 후 값 설정
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(schedule.getTheater().getId());
        theaterDto.setName(schedule.getTheater().getName());
        scheduleDto.setTheater(theaterDto);

        // Movie 객체 생성 후 값 설정
        Movie movie = new Movie();
        movie.setId(schedule.getMovie().getId());
        movie.setTitle(schedule.getMovie().getTitle());
        scheduleDto.setMovie(movie);

        return scheduleDto;
    }


//    public List<ScheduleDto> findAllSchedules() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        return schedules.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    // private long id;
    //    private TheaterDto theater;
    //    private Movie movie;
    //    private String schDate;
    //    private LocalDateTime time;


    //0218 - 특정 영화, 극장 지점 ,시간 조건에 맞는 스케쥴들 출력[test]
    public List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId_2(Long movieId, Long cinemaId, LocalDate schDate) {
        return scheduleRepository.findScheduleDetailsByDateAndCinemaId_2(movieId, cinemaId,schDate);
    }



    //origin
    public List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId(Long id, LocalDate schDate) {
        return scheduleRepository.findScheduleDetailsByDateAndCinemaId(id, schDate);
    }
}
