package com.sh.app.memberLikeGenre.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.member.entity.Member;
import com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberLikeGenreRepository extends JpaRepository<MemberLikeGenre, Long> {
    List<MemberLikeGenre> findByMember(Member member);

    @Query("SELECT new com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto(m.id, m.memberName, g.genreName, g.id) " +
            "FROM MemberLikeGenre mg " +
            "JOIN mg.member m " +
            "JOIN mg.genre g " +
            "WHERE m.id = :memberId")
    Optional<MemberLikeGenreListDto> findMemberLikeGenreListByMemberId(@Param("memberId") Long memberId);
}
