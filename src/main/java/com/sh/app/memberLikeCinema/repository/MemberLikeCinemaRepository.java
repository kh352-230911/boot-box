package com.sh.app.memberLikeCinema.repository;

import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface MemberLikeCinemaRepository extends JpaRepository<MemberLikeCinema, Long> {
    int deleteByCinemaId(Long cinemaId);

    @Query("SELECT mlc FROM MemberLikeCinema mlc JOIN FETCH mlc.cinema WHERE mlc.member.id = :memberId")
    List<MemberLikeCinema> findByMemberIdWithCinema(@Param("memberId") Long memberId);

    @Query("SELECT mlc, c.region_cinema FROM MemberLikeCinema mlc JOIN FETCH mlc.cinema c WHERE mlc.member.id = :memberId")
    List<MemberLikeCinema> findByMemberIdWithCinema2(@Param("memberId") Long memberId);

    int countByMemberId(Long memberId);

}
