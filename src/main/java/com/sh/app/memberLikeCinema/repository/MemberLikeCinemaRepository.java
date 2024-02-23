package com.sh.app.memberLikeCinema.repository;

import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLikeCinemaRepository extends JpaRepository<MemberLikeCinema, Long> {
    int deleteByCinemaId(Long cinemaId);

    List<MemberLikeCinema> findByMemberId(Long memberId);

    int countByMemberId(Long memberId);
}
