package com.sh.app.schedule.service;

import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleInfoDto;
import com.sh.app.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId(Long id, LocalDate schDate) {
        return scheduleRepository.findScheduleDetailsByDateAndCinemaId(id, schDate);
    }
}
