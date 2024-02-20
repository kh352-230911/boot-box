package com.sh.app.member.repository;

import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("from Member m join fetch m.authorities where m.memberLoginId = :username")
    Member findByMemberLoginId(String username);

    List<Member> findAll();

    @Query(value = """
        select
            m.id,
            m.member_login_id,
            m.member_pwd,
            m.member_email,
            m.member_name,
            m.member_phone,
            m.birthyear,
            r.id as reservation_id,
            r.member_id,
            r.schedule_id,
            r.status
        from
            member m join reservation r
                on m.id = r.member_id
        where 
            m.id = :id
    """, nativeQuery = true)
    Member findByReservation(Long id);
}
