package com.sh.app.cinema.service;

import com.sh.app.admin.dto.AdminListDto;
import com.sh.app.cinema.dto.CinemaManagementDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.memberLikeCinema.d.MemberLikeCinemaDto;
import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import com.sh.app.movie.dto.MovieListDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
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
        CinemaDto cinemaDto = convertToCinemaDto(cinema);

        List<MemberLikeCinemaDto> memberLikes = new ArrayList<>();
        for (MemberLikeCinema memberLike : cinema.getMemberLikeCinemas()) {
            MemberLikeCinemaDto dto = MemberLikeCinemaDto.builder()
                    .memberId(memberLike.getMember().getId())
                    .build();
            memberLikes.add(dto);
        }
        cinemaDto.setMemberLikeCinemas(memberLikes);

        return cinemaDto;
    }


    public String findRegion(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new EntityNotFoundException("Cinema not found with id: " + cinemaId));
        return cinema.getRegion_cinema();
    }

    public Optional<Cinema> findById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId);
    }

    public List<MovieListDto> getMoviesByCinemaId(Long id) {
        return cinemaRepository.findMoviesByCinemaId(id).stream()
                .map(movie -> modelMapper.map(movie, MovieListDto.class))
                .collect(Collectors.toList());
    }

}
