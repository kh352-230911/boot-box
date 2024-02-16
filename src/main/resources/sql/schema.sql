-- DDL 작성
drop table movie_genre;
drop sequence seq_movie_genre_id;
drop table member_like_genre;
drop sequence seq_member_like_genre_id;
drop table genre;
drop sequence seq_genre_id;
drop table reservation_seat;
drop sequence seq_reservation_seat_id;
drop table seat;
drop sequence seq_seat_id;
drop table order_pay;
drop table cancel_pay;
drop sequence seq_cancel_pay_id;
drop table review;
drop sequence seq_review_id;
drop table reservation;
drop table authority;
drop sequence seq_authority_id;
drop table answer;
drop sequence seq_answer_id;
drop table ask;
drop sequence seq_ask_id;
drop table member_like_cinema;
drop sequence seq_member_like_cinema_id;
drop table movie_list;
drop sequence seq_movie_list_id;
drop table member;
drop sequence seq_member_id;
drop table notice;
drop sequence seq_notice_id;
drop table admin;
drop sequence seq_admin_id;
drop table schedule;
drop sequence seq_schedule_id;
drop table theater;
drop table cinema;
drop table location;
drop table movie;


--17.지역
CREATE TABLE LOCATION(
    id number NOT NULL,
    location_name varchar2(50) NOT NULL,
    constraints pk_location_id primary key(id) --pk
);
--
-- 3.극장(ex강남점,성수점...)
CREATE TABLE CINEMA(
    id number	NOT NULL, --지점 아이디
    location_id number NOT NULL, --지역 아이디
    region_cinema varchar2(50) NOT NULL,--지점명
    theater_number number	NOT NULL, --상영관 수(1관,2관..)
    address	varchar2(500) NOT NULL, --주소
    location_lo number NOT NULL, --지도 경도
    location_la number NOT NULL, --지도 위도
    phone varchar2(100) NOT NULL, --전화번호
    constraints pk_cinema_id primary key(id), --pk
    constraints fk_cinema_location_id foreign key(location_id) references location(id) on delete set null, --지역 아이디 수정,삭제 시 자식 null로됨
    constraints uq_cinema_region_cinema unique(region_cinema) -- uq
);
--6.회원
CREATE TABLE MEMBER(
    id number NOT NULL, --시퀀스(pk)
    member_login_id varchar2(100) NOT NULL, --아이디(uq)
    member_pwd	varchar2(100) NOT NULL, --비밀번호
    member_email varchar2(100) NOT NULL,	--이메일(uq)
    member_name varchar2(50) NOT NULL, --이름
    member_phone varchar2(100) NOT NULL, --핸드폰번호
    birthyear varchar2(50) NOT NULL, -- 출생년도
    constraints pk_member_id primary key(id),
    constraints uq_member_member_login_id unique(member_login_id),
    constraints uq_member_member_email unique(member_email),
    constraints uq_member_member_phone unique(member_phone)
);
create sequence seq_member_id; --회원 시퀀스
--
--2.관리자
CREATE TABLE ADMIN(
    id number NOT NULL, --관리자 아이디
    cinema_id number, -- 극장 아이디
    username varchar2(50) NOT NULL, -- 관리자 로그인 아이디
    password varchar2(255)	NOT NULL, --비밀번호
    constraints pk_admin_id primary key(id), --pk
    constraints fk_admin_cinema_id foreign key(cinema_id) references cinema(id) on delete set null, -- fk
    constraints uq_admin_username unique(username) -- uq
);
create sequence seq_admin_id; -- 관리자 시퀀스
--
-- 18.권한
--0201 관리자1(최상위)/관리자2(지점)/회원
--일반회원  table / 관리자 table이 분리 되어있기 때문에 컬럼도 user/ admin으로 나뉘어있음
CREATE TABLE AUTHORITY(
    id number NOT NULL,
    member_id number NULL,
    admin_id number NULL,
    name varchar2(50) NOT NULL,
    constraints pk_authority_id primary key(id),
    constraints fk_authority_user_id foreign key(member_id) references member(id) on delete set null,
    constraints fk_authority_admin_id foreign key(admin_id) references admin(id) on delete set null,
    constraints ck_authority_name check(name in('ROLE_ADMIN','ROLE_MANAGER', 'ROLE_USER'))
);
create sequence seq_authority_id;
--
--4.좌석
CREATE TABLE SEAT(
    id number NOT NULL, --pk
    name varchar2(10) NOT NULL, --좌석 명 ex)A15,C09..
    constraints pk_seat_id primary key(id) --pk로 지정
);
create sequence seq_seat_id; --좌석 시퀀스
--
--5.상영관 (ex 1관,2관..)
CREATE TABLE THEATER (
    id number NOT NULL, --상영관 pk
    cinema_id number	NOT NULL, --극장id fk
    name varchar2(50)	 NOT NULL, --상영관명
    seat 	number	NOT NULL,--총 좌석수
    constraints pk_theater_id primary key(id), --pk로 지정
    constraints fk_theater_cinema_id foreign key(cinema_id) references cinema(id) on delete set null
);
--
--15.장르
CREATE TABLE GENRE(
    id	number NOT NULL, --pk
    genre_list varchar2(200) NOT NULL,
    constraints pk_genre_id primary key(id) --pk
);
create sequence seq_genre_id;
--
-- 1.예매 가능한 영화
CREATE TABLE MOVIE(
    id number NOT NULL , --영화 id (api로 받아올 때 - 고유값이라 겹치지 않음)
    title varchar2(500) NOT NULL, --영화 제목
    film_ratings varchar2(20) default 'NONE' NOT NULL, -- 관람등급
    release_date varchar2(20) NOT NULL, -- 개봉일(상영가능한 날짜의 첫 날)
    running_time number NOT NULL, --상영시간
    trailer	varchar2(500) NULL, --예고편
    poster varchar2(500) NULL, --포스터
    director varchar2(100) NULL, --감독
    actor	varchar2(500) NULL, --배우
    summary varchar2(4000) NULL, --줄거리(5000자 length에러 나서 4000으로 수정)
    advance_reservation number(4,1) NOT NULL, -- 예매율
    constraints pk_movie_id primary key(id), -- pk
    constraints ck_movie_film_ratings check(film_ratings in('ALL', 'TWELVE', 'FIFTEEN', 'EIGHTEEN', 'NONE')) -- ck
);
-- 시퀀스 사용x
--
--19.영화 장르
CREATE TABLE MOVIE_GENRE(
    id	number NOT NULL,
    genre_id number NOT NULL,
    movie_id number NOT NULL,
    constraint pk_movie_genre_id primary key(id),
    constraint fk_movie_genre_genre_id foreign key(genre_id) references genre(id) on delete set null,
    constraint fk_movie_genre_movie_id foreign key(movie_id) references movie(id) on delete set null
);
create sequence seq_movie_genre_id;
--
--20.회원이 선호하는 장르
CREATE TABLE MEMBER_LIKE_GENRE(
    id	number NOT NULL,
    member_id number NOT NULL,
    genre_id number NOT NULL,
    constraints pk_member_like_genre_id primary key(id),
    constraints fk_member_like_genre_member_id foreign key(member_id) references member(id) on delete set null,
    constraints fk_member_like_genre_genre_id foreign key(genre_id) references genre(id) on delete set null
);
create sequence seq_member_like_genre_id;
--
--16.회원 선호 극장(MEMBER_LIKE에서 알아보기 쉽게 수정)-브릿지테이블입니다.(회원-브릿지-극장)
CREATE TABLE MEMBER_LIKE_CINEMA(
    id number NOT NULL,--pk
    member_id  number NOT NULL, --fk
    cinema1_id number NULL, --fk
    cinema2_id number NULL, --fk
    cinema3_id number NULL, --fk
    constraints pk_member_like_cinema_id primary key(id), --pk
    constraints fk_member_like_cinema_member_id foreign key(member_id) references member(id) on delete set null,
    constraints fk_member_like_cinema_cinema1_id foreign key(cinema1_id) references cinema(id) on delete set null,
    constraints fk_member_like_cinema_cinema2_id foreign key(cinema2_id) references cinema(id) on delete set null,
    constraints fk_member_like_cinema_cinema3_id foreign key(cinema3_id) references cinema(id) on delete set null
);
create sequence seq_member_like_cinema_id; --선호극장 등록시 시퀀스
--
--7.문의
CREATE TABLE ASK(
    id number NOT NULL, --pk
    member_id number	NOT NULL, --회원 아이디 fk
    ask_title varchar2(100) NOT NULL,
    ask_detail varchar2(500) NOT NULL,
    created_at date DEFAULT current_date NOT NULL,
    constraints pk_ask_id primary key(id), --pk
    constraints fk_ask_member_id foreign key(member_id) references member(id) on delete set null --fk
);
create sequence seq_ask_id; --문의 시퀀스
--
--8.문의 답변
CREATE TABLE ANSWER(
    id number NOT NULL,--pk
    ask_id number NOT NULL,--문의 아이디 fk
    admin_id number NOT NULL, --관리자 아이디 fk
    content varchar2(2000) NOT NULL,
    created_at date DEFAULT sysdate NOT NULL,
    constraints pk_answer_id primary key(id), --pk
    constraints fk_answer_ask_id foreign key(ask_id) references ask(id) on delete set null,
    constraints fk_answer_admin_id foreign key(admin_id) references admin(id) on delete set null
);
create sequence seq_answer_id;
--
--9.공지사항
CREATE TABLE NOTICE(
    id number, --pk
    admin_id number NOT NULL, --관리자 아이디 fk
    notice_type varchar2(200) default 'ETC' NOT NULL, -- 공지유형 ck
    notice_title varchar2(100)	NOT NULL,
    notice_content varchar2(2000)	NOT NULL,
    constraints pk_notice_id primary key(id), --pk
    constraints fk_notice_admin_id foreign key(admin_id) references admin(id) on delete set null,
    constraint ck_notice_type check(notice_type in ('SYSTEM','CINEMA','EVENT','ETC'))
);
create sequence seq_notice_id;
--
--11.상영 시간표
CREATE TABLE SCHEDULE(
    id number NOT NULL,--pk
    theater_id number NOT NULL, --상영관아이디 fk
    movie_id number NOT NULL, --영화 id [코드값] fk
    sch_date varchar2(50) NOT NULL, --날짜
    time varchar2(50)	NOT NULL, --시작시간
    constraints pk_schedule_id primary key(id), --pk
    constraints fk_schedule_theater_id foreign key(theater_id) references theater(id) on delete set null,
    constraints fk_schedule_movie_id foreign key(movie_id) references movie(id) on delete set null
);
create sequence seq_schedule_id;
--
--13.★★예약[예매]
CREATE TABLE RESERVATION(
    id varchar2(50)	NOT NULL, -- 예약 아이디 pk
    member_id number NOT NULL, --회원 아이디 fk
    schedule_id number NOT NULL,--상영 일정 아이디 fk
    status varchar2(30) NOT NULL, --ck (pending,confirm)
    constraint pk_reservation_id primary key(id), --pk
    constraint ck_reservation_status check(status in ('PENDING','CONFIRM')),
    constraint fk_reservation_member_id foreign key(member_id) references member(id) on delete set null,
    constraint fk_reservation_schedule_id foreign key(schedule_id) references schedule(id) on delete set null
);
--
--21.예약좌석
CREATE TABLE RESERVATION_SEAT(
    id number NOT NULL,
    res_id	varchar2(50) NOT NULL,
    seat_id number	NOT NULL,
    constraints pk_reservation_seat_id primary key(id),
    constraints fk_reservation_seat_res_id foreign key(res_id) references reservation(id) on delete set null,
    constraints fk_reservation_seat_seat_id  foreign key(seat_id) references seat(id) on delete set null
);
create sequence seq_reservation_seat_id;
--
--14.주문 결제 테이블(결제 누적 테이블) 시퀀스x
CREATE TABLE ORDER_PAY(
  id varchar2(1000)	NOT NULL, --주문결제 아이디 pk
  reservation_id varchar2(50) NOT NULL, --예약 아이디 fk
  member_id number	NOT NULL, --회원아이디 fk
  imp varchar2(100)	NOT NULL, --가맹점 식별코드
  inicis varchar2(100) NOT NULL, --지원 pg사
  reservation_amount varchar2(100) NOT NULL,	--결제 방식
  price number NOT NULL, --총 결제 금액
  phone varchar2(100)	NOT NULL, --핸드폰 번호
  status varchar2(30)	 NOT NULL, --결제 상태
  constraints pk_order_pay_id primary key(id), --pk
  constraints fk_order_pay_reservation_id foreign key(reservation_id) references reservation(id) on delete set null,
  constraints ck_order_pay_status check(status in ('PENDING','CONFIRM')),
  constraints fk_order_pay_member_id foreign key(member_id) references member(id) on delete set null
);
--
--10.결제 취소 내역(완료된 결제를 취소했다는 가정하에 insert됨)
CREATE TABLE CANCEL_PAY(
   id number NOT NULL, --pk
   member_id number	NOT NULL, --fk
   c_reservation_pay number NOT NULL,
   c_reservation_amount VARCHAR(255) NOT NULL,
   constraints pk_cancel_pay_id primary key(id), --pk
   constraints fk_cancel_pay_member_id foreign key(member_id) references member(id) on delete set null
);
create sequence seq_cancel_pay_id;
--
--22.극장별 영화목록(브릿지)
CREATE TABLE MOVIE_LIST(
   id number NOT NULL, --PK
   movie_id number NOT NULL,--FK
   cinema_id number NOT NULL,--FK
   constraint pk_movie_list_id primary key(id),
   constraint fk_movie_list_movie_id foreign key(movie_id) references movie(id) on delete set null,
   constraint fk_movie_list_cinema_id foreign key(cinema_id) references cinema(id) on delete set null
);
create sequence seq_movie_list_id;
--
--12.리뷰
CREATE TABLE REVIEW(
    id number NOT NULL,--pk
    reservation_id varchar2(100)	NOT NULL, -- 예약내역 fk
    member_id number NOT NULL, --회원id fk
    movie_id number NOT NULL, -- 영화 id fk
    review_score number(2, 1) default 0 NOT NULL,--리뷰 평점
    review_detail varchar2(2000) NOT NULL,--리뷰 내용
    review_created_at date DEFAULT sysdate NOT NULL,--리뷰 작성날짜
    constraints pk_review_id primary key(id), --pk
    constraints fk_review_reservation_id foreign key(reservation_id) references reservation(id) on delete set null,
    constraints fk_review_member_id foreign key(member_id) references member(id) on delete set null,
    constraints fk_review_movie_id foreign key(movie_id) references movie(id) on delete set null
);
create sequence seq_review_id;