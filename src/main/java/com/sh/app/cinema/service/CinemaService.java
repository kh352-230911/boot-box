package com.sh.app.cinema.service;

import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private ModelMapper modelMapper;

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
    }
}
