package com.sh.app.memberLikeCinema.serviece;

import com.sh.app.location.dto.LocationResDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.repository.MemberRepository;
import com.sh.app.memberLikeCinema.dto.CreateMemberLikeCinemaDto;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaListDto;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaListDto2;
import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import com.sh.app.memberLikeCinema.repository.MemberLikeCinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberLikeCinemaService {
    @Autowired
    private MemberLikeCinemaRepository memberLikeCinemaRepository;
    @Autowired
    private ModelMapper modelMapper;


    public MemberLikeCinema bookMarkCreate(CreateMemberLikeCinemaDto createMemberLikeCinemaDto) {
        return memberLikeCinemaRepository.save(convertToMemberLikeCinema(createMemberLikeCinemaDto));
    }

    private MemberLikeCinema convertToMemberLikeCinema(CreateMemberLikeCinemaDto createMemberLikeCinemaDto) {
        MemberLikeCinema memberLikeCinema = modelMapper.map(createMemberLikeCinemaDto, MemberLikeCinema.class);
        return memberLikeCinema;
    }
    public int countByMemberId(Long memberId) {
        return memberLikeCinemaRepository.countByMemberId(memberId);
    }


    public int deleteByCinemaId(Long cinemaId) {
        int result = 0;
        result = memberLikeCinemaRepository.deleteByCinemaId(cinemaId);
        return result;

    }

    public List<MemberLikeCinemaListDto> findByMemberId(Long memberId) {
         return memberLikeCinemaRepository.findByMemberIdWithCinema(memberId)
                 .stream()
                 .map(this::convertToMemberLikeCinemaDto)
                 .collect(Collectors.toList());
    }

    private MemberLikeCinemaListDto convertToMemberLikeCinemaDto(MemberLikeCinema memberLikeCinema) {
        MemberLikeCinemaListDto memberLIkeCinemaListDto = modelMapper.map(memberLikeCinema, MemberLikeCinemaListDto.class);
        return memberLIkeCinemaListDto;
    }

    ////
    public List<MemberLikeCinemaListDto2> findByMemberId2(Long memberId) {
        return memberLikeCinemaRepository.findByMemberIdWithCinema2(memberId)
                .stream()
                .map(this::convertToMemberLikeCinemaDto2)
                .collect(Collectors.toList());
    }

    private MemberLikeCinemaListDto2 convertToMemberLikeCinemaDto2(MemberLikeCinema memberLikeCinema) {
        MemberLikeCinemaListDto2 memberLIkeCinemaListDto2 = modelMapper.map(memberLikeCinema, MemberLikeCinemaListDto2.class);
        return memberLIkeCinemaListDto2;
    }

//    public List<LocationResDto> findLikeCinema(Long memberId)
//    {
//        return memberLikeCinemaRepository.findByMemberIdWithCinema(memberId)
//                .stream()
//                .map(this::convertToMemberLikeCinemaDto2)
//                .collect(Collectors.toList());
//    }
//    private LocationResDto convertToMemberLikeCinemaDto2(LocationResDto locationResDto) {
//        LocationResDto locationResDto1 = modelMapper.map(memberLikeCinema, MemberLikeCinemaListDto.class);
//        return locationResDto1;
//    }




}
