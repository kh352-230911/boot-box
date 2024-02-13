package com.sh.app.cinema.service;

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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

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
    }
}
