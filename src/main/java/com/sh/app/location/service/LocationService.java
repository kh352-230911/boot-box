package com.sh.app.location.service;

import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.cinema.dto.CinemaTitleDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.entity.Location;
import com.sh.app.location.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;


    //지역이랑 지점 타이틀만 조회하는것(예매 메인에서 사용된다.)
//    public List<LocationDto> findAllLoactionsAndCinemaTitle() {
//        // 모든 Location과 그에 해당하는 Cinemas를 가져옵니다.
//        List<Location> locations = locationRepository.findAll();
//
//        // Location 엔터디를 LocationDto로 변환합니다.
//        return locations.stream()
//                .map(this::convertToLocationDto2)
//                .collect(Collectors.toList());
//    }
//
//    private LocationDto convertToLocationDto2(Location location) {
//        LocationDto locationDto = new LocationDto();
//        locationDto.setId(location.getId());
//        locationDto.setLocation_name(location.getLocation_name());
//
//        List<CinemaTitleDto> cinemaTitleDtos = location.getCinemas().stream()
//                .map(cinema -> convertToCinemaDto2(cinema)) // ModelMapper를 이용하거나, 적절한 변환 로직 적용
//                .collect(Collectors.toList());
//
//        locationDto.setCinemas(cinemaTitleDtos);
//
//        return locationDto;
//    }
//
//    private CinemaTitleDto convertToCinemaDto2(Cinema cinema) {
//        CinemaTitleDto cinemaTitleDto = modelMapper.map(cinema, CinemaTitleDto.class);
//        cinemaTitleDto.setLocation_name(
//                Optional.ofNullable(cinema.getLocation())
//                        .map((location) -> location.getLocation_name())
//                        .orElse(null)
//        );
//
//        return cinemaTitleDto;
//    }
    
    

    //-================================아래는 원본=========================
    public List<LocationDto> findAllLocationsWithCinemas() {
        // 모든 Location과 그에 해당하는 Cinemas를 가져옵니다.
        List<Location> locations = locationRepository.findAll();

        // Location 엔터디를 LocationDto로 변환합니다.
        return locations.stream()
                .map(this::convertToLocationDto)
                .collect(Collectors.toList());
    }

    private LocationDto convertToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setLocation_name(location.getLocation_name());

        List<CinemaDto> cinemaDtos = location.getCinemas().stream()
                .map(cinema -> convertToCinemaDto(cinema)) // ModelMapper를 이용하거나, 적절한 변환 로직 적용
                .collect(Collectors.toList());

        locationDto.setCinemas(cinemaDtos);

        return locationDto;
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
        cinemaDto.setTheater_number(cinema.getTheater_number());
        return cinemaDto;
    }
}
