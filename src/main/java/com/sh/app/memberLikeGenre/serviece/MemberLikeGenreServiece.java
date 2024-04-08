package com.sh.app.memberLikeGenre.serviece;

import com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import com.sh.app.memberLikeGenre.repository.MemberLikeGenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;


@Service
@Transactional
public class MemberLikeGenreServiece {

    @Autowired
    private MemberLikeGenreRepository memberLikeGenreRepository;

    public MemberLikeGenreListDto findMemberLikeGenreInfoByMemberId(Long memberId) {
        return memberLikeGenreRepository.findMemberLikeGenreListByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("선호 장르 정보를 찾을 수 없습니다."));
    }
}
