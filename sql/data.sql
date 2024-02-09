-- DML 작성

-- movie
insert into movie values (1, '웡카', 'ALL', '2024.01.31', 116,
    'https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4', 'http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg',
    '폴 킹', '티모시 샬라메', '세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!', 98);
insert into movie values (2, '시민덕희', 'FIFTEEN', '2024.01.24', 114,
    'https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4', 'http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg',
    '박영주', '라미란', '내 돈을 사기 친 그 놈이 구조 요청을 해왔다!', 99);
insert into movie values (3, '도그데이즈', 'TWELVE', '2024.02.07', 120,
    'http://h.vod.cgv.co.kr/vodCGVa/87978/87978_222744_1200_128_960_540.mp4', 'http://file.koreafilm.or.kr/thm/02/99/18/31/tn_DPK021652.jpg',
    '김덕민', '윤여정', '깔끔한 성격의 계획형 싱글남 ‘민상’(유해진).영끌까지 모아 산 건물을 개똥밭으로 만드는 세입자 수의사 ‘진영’(김서형) 때문에 매일 머리가 아프다.오늘도 ‘진영’과 티격태격하던 ‘민상’은 동물병원에서 한 성격하는 할머니를 만나는데...', 99);
insert into movie values (4, '데드맨', 'FIFTEEN', '2024.02.07', 108,
    'http://h.vod.cgv.co.kr/vodCGVa/87981/87981_222534_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87981/87981_1000.jpg',
    '하준원', '조진웅', '목숨값 단돈 500만원! 이름값 1000억? 이름에 살고, 이름에 죽는다!', 97);
insert into movie values (5, '아가일', 'TWELVE', '2024.02.07', 139,
    'http://h.vod.cgv.co.kr/vodCGVa/87426/87426_220043_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87426/87426_1000.jpg',
    '매튜 본', '헨리 카빌', '내가 쓴 베스트셀러 스파이 소설이 현실이 되었습니다?!', 90);
--
-- -- location
-- insert into LOCATION values (seq_location_id.nextval, '서울');
-- insert into LOCATION values (seq_location_id.nextval, '경기');
-- insert into LOCATION values (seq_location_id.nextval, '인천');
-- insert into LOCATION values (seq_location_id.nextval, '대전/충청');
-- insert into LOCATION values (seq_location_id.nextval, '부산/울산');
--
-- -- cinema
-- insert into CINEMA values (seq_cinema_id.nextval, 1, '강남점', 1, '서울특별시 강남구 강남대로 438', 127.02629392376384, 37.501674084777505, '1544-1122');
-- insert into CINEMA values (seq_cinema_id.nextval, 2, '고양백석점', 1, '경기도 고양시 일산동구 백석동 1242', 126.78965179914998, 37.643461526992546, '1544-1122');
-- insert into CINEMA values (seq_cinema_id.nextval, 3, '청라점', 1, '인천광역시 서구 청라동 155-7 스퀘어세븐', 126.64164011661255, 37.53237333861732, '1544-1122');
-- insert into CINEMA values (seq_cinema_id.nextval, 4, '대전점', 1, '대전광역시 중구 문화동 1-16', 127.40842431841456, 36.32061814895665, '1544-1122');
-- insert into CINEMA values (seq_cinema_id.nextval, 5, '센텀시티점', 1, '부산광역시 해운대구 우동 1495번지 신세계센텀시티', 129.13033686916697 , 35.16911956001504, '1544-1122');
--
-- -- admin
-- insert into ADMIN values (seq_admin_id.nextval, 1, 'adm220932', '1234');
-- insert into ADMIN values (seq_admin_id.nextval, 2, 'adm650704', '1234');
-- insert into Admin values (seq_admin_id.nextval, 3, 'adm392258', '1234');
-- insert into ADMIN values (seq_admin_id.nextval, 4, 'adm852542', '1234');
-- insert into ADMIN values (seq_admin_id.nextval, 5, 'adm724198', '1234');
-- insert into ADMIN values (seq_admin_id.nextval, null, 'adm322100', '1234');
--
-- -- seat
-- insert into SEAT values (seq_seat_id.nextval, 'A01');
-- insert into SEAT values (seq_seat_id.nextval, 'B01');
-- insert into SEAT values (seq_seat_id.nextval, 'C01');
-- insert into SEAT values (seq_seat_id.nextval, 'D01');
-- insert into SEAT values (seq_seat_id.nextval, 'E01');
--
-- -- theater
-- insert into THEATER values (seq_theater_id.nextval, 1, '1관', 60);
-- insert into THEATER values (seq_theater_id.nextval, 2, '1관', 60);
-- insert into THEATER values (seq_theater_id.nextval, 3, '1관', 60);
-- insert into THEATER values (seq_theater_id.nextval, 4, '1관', 60);
-- insert into THEATER values (seq_theater_id.nextval, 5, '1관', 60);
--
-- -- member
-- insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
-- values(seq_member_id.nextVal, 'rhgPwls','1234','update_set@naver.com','고혜진','01012341234','1990') ;
-- insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
-- values(seq_member_id.nextVal, 'testUser','1234','testUser1234@naver.com','김테스트','01012345678','1993') ;
-- insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
-- values(seq_member_id.nextVal, 'sinsa','1234','sinsa@naver.com','신사임당','01011112222','1996');
-- insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
-- values(seq_member_id.nextVal, 'honggd','1234','honggd@naver.com','홍길동','01022221111','2000');
-- insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
-- values(seq_member_id.nextVal, 'less','1234','leess@naver.com','이순신','01013225521','1989');
--
-- genre
insert into GENRE values (seq_genre_id.nextval, '드라마');
insert into GENRE values (seq_genre_id.nextval, '액션');
insert into GENRE values (seq_genre_id.nextval, '코미디');
insert into GENRE values (seq_genre_id.nextval, '판타지');
insert into GENRE values (seq_genre_id.nextval, '범죄');
--
-- -- authority
-- insert into AUTHORITY values (seq_authority_id.nextval, 1, null, 'ROLE_USER');
-- insert into AUTHORITY values (seq_authority_id.nextval, 2, null, 'ROLE_USER');
-- insert into AUTHORITY values (seq_authority_id.nextval, 3, null, 'ROLE_USER');
-- insert into AUTHORITY values (seq_authority_id.nextval, 4, null, 'ROLE_USER');
-- insert into AUTHORITY values (seq_authority_id.nextval, 5, null, 'ROLE_USER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 1, 'ROLE_MANAGER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 2, 'ROLE_MANAGER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 3, 'ROLE_MANAGER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 4, 'ROLE_MANAGER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 5, 'ROLE_MANAGER');
-- insert into AUTHORITY values (seq_authority_id.nextval, null, 6, 'ROLE_ADMIN');
--
-- -- ask
--insert into ASK values (seq_ask_id.nextval, 1, '예매 취소 및 환불', '예매 취소 및 환불 규정은 어떻게 되나요?');
--insert into ASK values (seq_ask_id.nextval, 2, '영화 시간보다 늦었어요.', ' 영화 시간보다 늦었어요. 입장 가능한가요?');
--insert into ASK values (seq_ask_id.nextval, 3, '음식물 반입', '상영관 내 다른 음식물의 반입이 되나요?');
--insert into ASK values (seq_ask_id.nextval, 4, '관람 등급', '관람 등급에 대해 알고 싶습니다.');
--insert into ASK values (seq_ask_id.nextval, 5, '영화 관람을 하다 소지품을 분실했어요', '영화 관람을 하다 소지품을 분실했어요. 분실물은 어떻게 찾나요?');
--
-- -- answer
--insert into ANSWER values (seq_answer_id.nextval, 1, 6, '상영시간 이전까지만 가능하며, 상영시간 이후 취소나 환불은 되지 않습니다.');
--insert into ANSWER values (seq_answer_id.nextval, 2, 6, '영화 시간 내 언제든 입장이 가능합니다.');
--insert into ANSWER values (seq_answer_id.nextval, 3, 6, '상영관 내 외부 음식물 반입은 가능합니다.');
--insert into ANSWER values (seq_answer_id.nextval, 4, 6, 'CGV는 영화진흥법에 의한 영화별 관람등급을 철저히 준수하고 있습니다.');
--insert into ANSWER values (seq_answer_id.nextval, 5, 6, '분실물의 경우 발견 즉시 현장에서 보관을 진행하고 있으며, 확인 요청시 본인 확인 후 물품 확인을 도와드리고 있습니다.');
--
-- -- notice
--insert into NOTICE values (seq_notice_id.nextval, 1, 6, 'SYSTEM', '2023년 12월 시스템점검 안내', '원활하고 안정된 서비스 제공을 위하여 2023년 12월 새벽 시스템 점검 작업이 예정되어 있습니다.');
--insert into NOTICE values (seq_notice_id.nextval, 2, 6, 'CINEMA', '[센텀시티] 백화점 휴점에 따른 극장 이용 안내', '22.09.09(금)~22.09.10(토) 신세계 백화점 센텀시티점 휴점에 따라 극장이용 동선 안내를 공지하오니 이용에 착오 없으시길 바랍니다.');
--insert into NOTICE values (seq_notice_id.nextval, 3, 6, 'EVENT', '신규 초콜릿향 진행극장 변경 안내 ', '4DX 신규 초콜릿향 진행 극장 (1/31~2/6) CGV 계양, 광교, 광주터미널, 김해, 대구, 대구스타디움');
--insert into NOTICE values (seq_notice_id.nextval, 4, 6, 'ETC', '개인정보처리방침 개정 공지 (23.11.06 시행)', '2023년 11월 6일 자로 BOOTBOX의 개인정보처리방침이 개정됨에 따라 회원님께 주요 개정 내용과 적용 일정을 아래와 같이 안내 드립니다.');
--insert into NOTICE values (seq_notice_id.nextval, 5, 6, 'ETC', 'BOOTBOX 서비스 이용약관 개정 안내 (2024년 1월 23일 시행)', '2024년 1월 23일자로 BOOTBOX 서비스 이용약관을 일부 개정하게 되어 아래와 같이 사전 안내드립니다. 서비스 이용에 참고 부탁드립니다.');
--
-- -- schedule
--insert into SCHEDULE values (seq_schedule_id.nextval, 1, 1, '2024-02-01', '15:00');
--insert into SCHEDULE values (seq_schedule_id.nextval, 2, 1, '2024-02-01', '20:00');
--insert into SCHEDULE values (seq_schedule_id.nextval, 3, 1, '2024-02-03', '10:00');
--insert into SCHEDULE values (seq_schedule_id.nextval, 4, 1, '2024-02-04', '22:00');
--insert into SCHEDULE values (seq_schedule_id.nextval, 5, 1, '2024-02-06', '19:00');
--
-- -- reservation
--insert into RESERVATION values ('box16443', 1, 1, 'PENDING');
--insert into RESERVATION values ('box25822', 2, 2, 'CONFIRM');
--insert into RESERVATION values ('box47220', 3, 3, 'CONFIRM');
--insert into RESERVATION values ('box42217', 4, 4, 'CONFIRM');
--insert into RESERVATION values ('box32582', 5, 5, 'PENDING');
--
-- -- reservation_seat
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'order1644325835123', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'order1644325822914', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'order1722018573211', 3);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'order1882210442217', 4);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'order1644325822955', 5);
--
-- -- order_pay
--insert into ORDER_PAY values ('order1644325835123', 'box16443', 1, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012341234', 'PENDING');
--insert into ORDER_PAY values ('order1414325835223', 'box25822', 2, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012345678', 'PENDING');
--insert into ORDER_PAY values ('order1544325425262', 'box47220', 3, 'imp32105587', 'html5_inicis', 'card', 12000 , '01011112222', 'PENDING');
--insert into ORDER_PAY values ('order2142325845122', 'box42217', 4, 'imp32105587', 'html5_inicis', 'card', 12000 , '01022221111', 'PENDING');
--insert into ORDER_PAY values ('order2244325135126', 'box32582', 5, 'imp32105587', 'html5_inicis', 'card', 12000 , '01013225521', 'PENDING');
--
-- -- cancel_pay
--insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 1, 12000, 'card');
--insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 2, 12000, 'card');
--insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 3, 12000, 'card');
--insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 4, 12000, 'card');
--insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 5, 12000, 'card');
--
-- -- review
--insert into REVIEW values (seq_review_id.nextval, 'box16443', 1, 3, '너무 지루해요', default);
--insert into REVIEW values (seq_review_id.nextval, 'box25822', 2, 4, '최고', default);
--insert into REVIEW values (seq_review_id.nextval, 'box47220', 3, 5, '시간 가는 줄 모르고 봤어용', default);
--insert into REVIEW values (seq_review_id.nextval, 'box42217', 4, 5, '짱짱', default);
--insert into REVIEW values (seq_review_id.nextval, 'box32582', 5, 2, '재미없어요', default);
-- -- 브릿지
--
-- movie_genre
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 4, 1);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 1);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 2);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 3);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 5, 4);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 2, 5);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 5);

insert into MOVIE_GENRE values (4, 1);
insert into MOVIE_GENRE values (1, 1);
insert into MOVIE_GENRE values (1, 2);
insert into MOVIE_GENRE values (1, 3);
insert into MOVIE_GENRE values (5, 4);
insert into MOVIE_GENRE values (2, 5);
insert into MOVIE_GENRE values (3, 5);
--
-- -- member_like_genre
-- insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 1, 1);
-- insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 2, 1);
-- insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 3, 4);
-- insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 4, 3);
-- insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 5, 2);
--
-- -- movie_list
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 1);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 2);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 2);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 4);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 3);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 4);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 5);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 3);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 1);
-- insert into MOVIE_LIST values (seq_movie_list_id.nextval, 5, 5);
--
-- -- member_like_cinema
--insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 1, 1, 2, 3);
--insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 2, 1, null, null);
--insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 3, 2, 5, null);
--insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 4, 4, null, null);
--insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 5, 2, 3, 5);
