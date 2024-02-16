package com.sh.app.cinema.service;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import com.sh.app.cinema.dto.CinemaDto;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // address, phone, 위도, 경도 설정추가
        cinemaDto.setAddress(cinema.getAddress());
        cinemaDto.setPhone(cinema.getPhone());
        cinemaDto.setLocation_lo(cinema.getLocation_lo());
        cinemaDto.setLocation_la(cinema.getLocation_la());
        return cinemaDto;
    }

    public CinemaDto getCinemaDetails(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cinema not found with id: " + id));
        return convertToCinemaDto(cinema);
    }
}
