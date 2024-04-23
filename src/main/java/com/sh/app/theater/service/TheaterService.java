package com.sh.app.theater.service;

import com.sh.app.theater.dto.TheaterDto;
import com.sh.app.theater.entity.Theater;
import com.sh.app.theater.repository.TheaterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<TheaterDto> findAllTheatersWithCinemaId(Long cinemaId) {
        System.out.println("========== 상영관 리스트 조회 ==========");
        List<Theater> theaters = theaterRepository.findByCinemaId(cinemaId, Sort.by(Sort.Direction.ASC, "id"));
        return theaters.stream()
                .map(theater -> modelMapper.map(theater, TheaterDto.class))
                .collect(Collectors.toList());
    }

    public void deleteTheaterWithId(Long deleteId) {
        theaterRepository.deleteById(deleteId);
    }

    public void createTheater(Long theaterId, Long cinemaId, String theaterName, int theaterSeat) {
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theaterId);
        theaterDto.setCinemaId(cinemaId);
        theaterDto.setName(theaterName);
        theaterDto.setSeat(theaterSeat);

        Theater theater = modelMapper.map(theaterDto, Theater.class);
        theaterRepository.save(theater);
    }
}
