package com.sh.app.member.repository;

import com.sh.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("from Member m join fetch m.authorities where m.memberLoginId = :username")
    Member findByMemberLoginId(String username);

    @Query("from Member order by id")
    List<Member> findAll();
}
