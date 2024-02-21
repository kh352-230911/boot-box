package com.sh.app.schedule.service;


import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;

import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.repository.ScheduleRepository;
import com.sh.app.seat.entity.SeatDto;
import com.sh.app.theater.dto.TheaterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<ScheduleDto> findScheduleWithTheaterId(Long theaterId) {
        List<Schedule> schedules = scheduleRepository.findByTheaterId(theaterId);
        return schedules.stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDto.class))
                .collect(Collectors.toList());

    }

    public List<Map<String, Object>> organizeSchedules(List<IScheduleInfoDto> scheduleDetails) {
        // 영화별, 상영관별, 스케줄별 그룹화하기위한 맵
        Map<String, Map<String, List<Map<String, Object>>>> organized = new HashMap<>();
        // 각 영화별 상영 시간을 저장하기 위한 맵
        Map<String, String> movieDurations = new HashMap<>();
        // 각 영화별 관람 등급을 저장하기 위한 맵
        Map<String, String> movieFilmRatings = new HashMap<>();

        // 데이터 각 행마다 반복
        for (IScheduleInfoDto dto : scheduleDetails) {
            Long movieId = dto.getMovieId();
            Long cinemaId = dto.getCinemaId();
            Long schId = dto.getSchId();
            LocalDateTime _schDate = dto.getSchDate();

            // LocalDateTime을 yyyy/MM/dd 형식의 문자열로 변환
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String schDate = _schDate.format(dateFormatter);

            // 예약 페이지 URL 생성 - 파라미터로 전달
            String bookingUrl = String.format("/bootbox/reservation/reservationBooking.do?movieId=%d&cinemaId=%d&schId=%d&schDate=%s",
                    movieId, cinemaId, schId, schDate);

            String filmRatings = dto.getFilmRatings();
            String title = dto.getMovieTitle();
            String theater = dto.getTheaterName();
            String runningTime = dto.getRunningTime();

            movieDurations.putIfAbsent(title, runningTime); // 영화 제목에 해당하는 상영 시간을 맵에 저장
            movieFilmRatings.putIfAbsent(title, filmRatings); // 영화 제목에 해당하는 관람등급을 맵에 저장

            // 영화 시간, 남은 좌석 그룹화 및 예약 페이지 링크 추가
            Map<String, Object> timeMap = new HashMap<>();
            timeMap.put("time", dto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            timeMap.put("seatsAvailable", dto.getRemainingSeats());
            timeMap.put("bookingUrl", bookingUrl); // 예약 페이지로 이동할 URL 추가

            // 영화 제목별, 상영관별, 스케줄별에 따라 그룹화
            organized.computeIfAbsent(title, k -> new HashMap<>())
                    .computeIfAbsent(theater, k -> new ArrayList<>())
                    .add(timeMap);
        }

        // 데이터 가공하여 최종적으로 필요한 상영일정 구조로 변환
        List<Map<String, Object>> finalStructure = new ArrayList<>();
        organized.forEach((movieTitle, theaters) -> {
            // 영화 제목별 그룹화
            Map<String, Object> movieMap = new HashMap<>();
            movieMap.put("title", movieTitle);
            movieMap.put("filmRatings", movieFilmRatings.get(movieTitle)); // 영화 제목에 해당하는 관람등급을 사용
            movieMap.put("totalDuration", movieDurations.get(movieTitle)); // 영화 제목에 해당하는 상영 시간을 사용

            // 상영관별 스케줄 그룹화
            List<Map<String, Object>> theaterList = new ArrayList<>();
            theaters.forEach((theaterName, timesList) -> {
                Map<String, Object> theaterMap = new HashMap<>();
                theaterMap.put("theater", theaterName);
                theaterMap.put("times", timesList);
                theaterList.add(theaterMap);
            });

            movieMap.put("schedules", theaterList);
            finalStructure.add(movieMap);
        });

        return finalStructure;
    }
}
