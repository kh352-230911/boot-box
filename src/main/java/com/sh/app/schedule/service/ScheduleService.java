package com.sh.app.schedule.service;


import com.sh.app.movie.entity.Movie;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * schedule이 단독으로 쓰일일은 거의 없지만 현재 db에 저장한 시험데이터를 가져오기 위해 만들음.
 */
@Service
@Transactional //기존 rollback처리 class단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class ScheduleService
{
    @Autowired
    ScheduleRepository scheduleRepository;

//    public List<ScheduleDto> findAll() {
//        return scheduleRepository.findAll();
//    }
}
