package com.sh.app.cinema.service;

<<<<<<< HEAD
import com.sh.app.cinema.dto.CinemaDTO;
import com.sh.app.cinema.dto.CinemaDetailsDTO;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import com.sh.app.schedule.dto.ScheduleDTO;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.repository.ScheduleRepository;
import com.sh.app.theater.dto.TheaterDTO;
import com.sh.app.theater.entity.Theater;
import com.sh.app.theater.repository.TheaterRepository;
=======
import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
>>>>>>> 1b6b4f080b44550d25f13efbd053eefae71a70d8
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.stream.Collectors;
=======
import java.util.Optional;
>>>>>>> 1b6b4f080b44550d25f13efbd053eefae71a70d8

@Service
@Transactional
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private ModelMapper modelMapper;

<<<<<<< HEAD
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public CinemaDetailsDTO getCinemaDetails(Long id) {
        CinemaDetailsDTO cinemaDetailsDTO = new CinemaDetailsDTO();

        List<Theater> theaters = theaterRepository.findByCinemaId(id);
        cinemaDetailsDTO.setTheaters(theaters.stream()
                .map(theater -> modelMapper.map(theater, TheaterDTO.class))
                .collect(Collectors.toList()));

        for (TheaterDTO theater : cinemaDetailsDTO.getTheaters()) {
            List<Schedule> schedules = scheduleRepository.findByTheaterId(theater.getId());
            theater.setSchedules(schedules.stream()
                    .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                    .collect(Collectors.toList()));
        }

        return cinemaDetailsDTO;
=======
    public Page<CinemaDto> findAll(Pageable pageable) {
        Page<Cinema> cinemaPage = cinemaRepository.findAll(pageable);
        return cinemaPage.map((cinema) -> convertToCinemaDto(cinema));
    }

    private CinemaDto convertToCinemaDto(Cinema cinema) {
        CinemaDto cinemaDto = modelMapper.map(cinema, CinemaDto.class);
        cinemaDto.setLocation_name(
                Optional.ofNullable(cinema.getLocation())
                        .map((location) -> location.getLocation_name())
                        .orElse(null)
        );
        return cinemaDto;
>>>>>>> 1b6b4f080b44550d25f13efbd053eefae71a70d8
    }
}
