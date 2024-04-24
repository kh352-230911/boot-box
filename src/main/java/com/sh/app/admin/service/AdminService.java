package com.sh.app.admin.service;

import com.sh.app.admin.dto.AdminListDto;
import com.sh.app.admin.entity.Admin;
import com.sh.app.admin.repository.AdminRepository;
import com.sh.app.cinema.dto.CinemaManagementDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import com.sh.app.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CinemaRepository cinemaRepository;

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public List<AdminListDto> findAllWithCinema() {
        List<Admin> admins = adminRepository.findAll(); // 가정: 모든 admin을 가져오는 메서드
        List<AdminListDto> adminListDtos = admins.stream()
                .map(this::convertToAdminListDto)
                .collect(Collectors.toList());

        // 모든 Cinema Ids 수집
        Set<Long> cinemaIds = adminListDtos.stream()
                .map(AdminListDto::getCinemaId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 한 번의 쿼리로 모든 Cinema 정보 가져오기
        Map<Long, Cinema> cinemaMap = cinemaRepository.findAllById(cinemaIds)
                .stream()
                .collect(Collectors.toMap(Cinema::getId, Function.identity()));

        // 각 AdminListDto에 Cinema 정보 매핑
        adminListDtos.forEach(dto -> {
            if (dto.getCinemaId() != null) {
                Cinema cinema = cinemaMap.get(dto.getCinemaId());
                if (cinema != null) {
                    // CinemaManagementDto 생성 또는 다른 필요한 작업 수행
                    CinemaManagementDto cinemaManagementDto = convertToCinemaManagementDto(cinema);
                    dto.setCinemaManagementDto(cinemaManagementDto);
                }
            }
        });

        return adminListDtos;
    }

    private CinemaManagementDto convertToCinemaManagementDto(Cinema cinema) {
        return modelMapper.map(cinema, CinemaManagementDto.class);
    }

    private AdminListDto convertToAdminListDto(Admin admin) {
        return modelMapper.map(admin, AdminListDto.class);
    }
}
