package com.sh.app.memberLikeGenre.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.member.entity.Member;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLikeGenreRepository extends JpaRepository<MemberLikeGenre, Long> {
    List<MemberLikeGenre> findByMember(Member member);

}
