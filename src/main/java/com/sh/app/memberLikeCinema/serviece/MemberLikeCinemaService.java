package com.sh.app.memberLikeCinema.serviece;

import com.sh.app.member.entity.Member;
import com.sh.app.member.repository.MemberRepository;
import com.sh.app.memberLikeCinema.dto.CreateMemberLikeCinemaDto;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaListDto;
import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import com.sh.app.memberLikeCinema.repository.MemberLikeCinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
             return memberLikeCinemaRepository.findByMemberId(memberId)
                .stream()
                .map((memberLikeCinema) -> convertToMemberLikeCinemaDto(memberLikeCinema))
                .collect(Collectors.toList());
    }

    private MemberLikeCinemaListDto convertToMemberLikeCinemaDto(MemberLikeCinema memberLikeCinema) {
        MemberLikeCinemaListDto memberLIkeCinemaListDto = modelMapper.map(memberLikeCinema, MemberLikeCinemaListDto.class);
        return memberLIkeCinemaListDto;
    }


}
