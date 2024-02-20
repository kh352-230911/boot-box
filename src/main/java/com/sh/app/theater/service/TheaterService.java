package com.sh.app.theater.service;

import com.sh.app.theater.dto.TheaterDto;
import com.sh.app.theater.entity.Theater;
import com.sh.app.theater.repository.TheaterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<TheaterDto> findAllTheatersWithCinemaId(Long cinemaId) {
        List<Theater> theaters = theaterRepository.findByCinemaId(cinemaId);
        return theaters.stream()
                .map(theater -> modelMapper.map(theater, TheaterDto.class))
                .collect(Collectors.toList());
    }
}
