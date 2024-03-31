package com.sh.app.member.repository;

import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("from Member m join fetch m.authorities where m.memberLoginId = :username")
    Member findByMemberLoginId(String username);

    @Query("from Member order by id asc")
    @EntityGraph(attributePaths = {"reservations"})
    List<Member> findAll();

//    @Query(value = """
//        select
//            m.id,
//            m.member_login_id,
//            m.member_pwd,
//            m.member_email,
//            m.member_name,
//            m.member_phone,
//            m.birthyear,
//            r.id as reservation_id,
//            r.member_id,
//            r.schedule_id,
//            r.status
//        from
//            member m join reservation r
//                on m.id = r.member_id
//        where
//            m.id = :id
//    """, nativeQuery = true)
//    Member findByReservation(Long id);

//    @Query("select m from Member m join fetch m.reservations where m.id = :id")
    @Query("SELECT m FROM Member m JOIN FETCH m.reservations r JOIN FETCH r.seats JOIN FETCH r.schedule s JOIN FETCH s.movie JOIN FETCH s.theater t JOIN FETCH t.cinema c JOIN FETCH c.location WHERE m.id = :id")
    Optional<Member> findByReservation(Long id);

    Optional<Member> findById(Long memberId);

}
