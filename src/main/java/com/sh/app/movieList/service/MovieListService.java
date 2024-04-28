package com.sh.app.movieList.service;

import com.sh.app.common.Approve;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movieList.dto.AddMovieListDto;
import com.sh.app.movieList.dto.ShowMovieListDto;
import com.sh.app.movieList.entity.MovieList;
import com.sh.app.movieList.repository.MovieListRepository;
import com.sh.app.schedule.dto.CreateScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MovieListService
{
    @Autowired
    MovieListRepository movieListRepository;
    @Autowired
    private ModelMapper modelMapper; // ModelMapper를 사용하여 엔티티를 DTO로 변환.
    public void addNewMovie(Long cinemaId, Long movieId)
    {
        AddMovieListDto addMovieListDto = new AddMovieListDto();

        addMovieListDto.setCinemaId(cinemaId);
        addMovieListDto.setMovieId(movieId);
        MovieList movieList = modelMapper.map(addMovieListDto, MovieList.class);
        movieListRepository.save(movieList);

    }

    public void deleteMovieMyCinema(Long id)
    {
        System.out.println("deleteMovieMyCinema - service");
        movieListRepository.deleteById(id);
    }


    //0426 새로 수정한 내 지점 상영영화 정보 불러오기..
    public List<ShowMovieListDto> showMovieListDtos(Long cinemaId)
    {  return movieListRepository.findMoviesByCinemaId(cinemaId).stream()
            .map(movieList -> modelMapper.map(movieList, ShowMovieListDto.class))
            .collect(Collectors.toList());
    }
}
