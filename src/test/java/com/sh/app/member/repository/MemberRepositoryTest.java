package com.sh.app.member.repository;

import com.sh.app.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * 0206 회원 가입 test
 * insert만 테스트 합니다!!
 *
 */
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("회원가입을 한 건 실행합니다.")
    @Test
    void test1()
    {
        //given
        Member member = Member.builder()
                .memberLoginId("imnongdamgom")
                .memberPwd("9999")
                .memberName("담곰이2")
                .memberEmail("damgom2024@naver.com")
                .memberPhone("01012345555")
                .birthyear("1997")
                .build();

        //when
        memberRepository.save(member);
        System.out.println(member);

        //then
        assertThat(member.getId()).isNotNull().isNotZero();

    }
}