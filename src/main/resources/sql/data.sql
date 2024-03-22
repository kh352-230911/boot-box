-- DML 작성
-- seat
-- 프로시저 반복문, 좌석 총 60개 (A~F열, 한 열당 10개)
--set serveroutput on;
--BEGIN
--FOR i IN ASCII('A')..ASCII('F') LOOP
--     FOR j IN 1..10 LOOP
--       INSERT INTO SEAT VALUES (seq_seat_id.nextval, CHR(i) || LPAD(TO_CHAR(j), 2, '0'));
--END LOOP;
--END LOOP;
--COMMIT;
--END;
--
-- location
insert into LOCATION values (02, '서울');
insert into LOCATION values (031, '경기');
insert into LOCATION values (032, '인천');
insert into LOCATION values (033, '강원');
insert into LOCATION values (042, '대전/충청');
insert into LOCATION values (053, '대구');
insert into LOCATION values (051, '부산/울산');
insert into LOCATION values (054, '경상');
insert into LOCATION values (062, '광주/전라/제주');
--
-- cinema
insert into CINEMA values (0201, 02, '강남점', 10, '서울특별시 강남구 강남대로 438', 127.026353, 37.5016383, '1544-1122');
insert into CINEMA values (0202, 02, '왕십리점', 33, '서울특별시 성동구 왕십리광장로 17 (행당동, 왕십리 민자역사 5층)', 127.038416, 37.5610558, '1544-1122');
insert into CINEMA values (03101, 031, '고양백석점', 7, '경기도 고양시 일산동구 백석동 1242', 126.789637, 37.6431586, '1544-1122');
insert into CINEMA values (03201, 032, '청라점', 8, '인천광역시 서구 청라동 155-7 스퀘어세븐', 126.641333, 37.5324919, '1544-1122');
insert into CINEMA values (04201, 042, '대전점', 9, '대전광역시 중구 문화동 1-16', 127.408797, 36.3206265, '1544-1122');
insert into CINEMA values (04202, 042, '논산점', 6, '충청남도 논산시 시민로 181 시네마타워 3층', 127.097331, 36.1833641, '1544-1122');
insert into CINEMA values (05101, 051, '센텀시티점', 8, '부산광역시 해운대구 우동 1495번지 신세계센텀시티', 129.129529 , 35.1688397, '1544-1122');
--지역에 극장 추가, 지역마다 최소 극장 2개 이상 , 광역시 이상 단위는 5개, 추가한 극장목록
insert into CINEMA values (0203, 02, '건대입구점', 12, '서울시 광진구 아차산로 30길 26 몰오브케이 3층', 127.066919, 37.5397710, '1544-1122');
insert into CINEMA values (0204, 02, '대학로점', 11, '서울특별시 종로구 명륜2가 41-9', 126.999820, 37.5834422, '1544-1122');
insert into CINEMA values (0205, 02, '명동점', 15, '서울특별시 중구 명동길 14 (명동)', 126.982866, 37.5633937, '1544-1122');
insert into CINEMA values (0206, 02, '상봉점', 15, '서울시 중랑구 상봉동 79-9 상봉듀오트리스 B2F', 127.087104, 37.5984575, '1544-1122');
insert into CINEMA values (0207, 02, '수유점', 9, '서울시 강북구 수유동 168-12', 127.029788, 37.6423901, '1544-1122');
insert into CINEMA values (0208, 02, '여의도점', 9, '	서울특별시 영등포구 여의도동 국제금융로 10번지 국제금융센터 지하3층', 126.925282, 37.5249613, '1544-1122');
insert into CINEMA values (0209, 02, '영등포점', 10, '서울특별시 영등포구 영등포동 4가 441-10번지 경방 타임스퀘어 4~7층', 126.903560, 37.5178725, '1544-1122');
insert into CINEMA values (0210, 02, '용산아이파크몰', 11, '서울특별시 용산구 한강대로23길 55, 6층(한강로동)', 126.963935, 37.5295840, '1544-1122');

insert into CINEMA values (03102, 031, '광교점', 6, '경기도 수원시 영통구 광교중앙로 124 갤러리아백화점 광교점 10층', 127.057405, 37.2851854, '1544-1122');
insert into CINEMA values (03103, 031, '광명역', 7, '경기도 광명시 일직로 43 GIDC B동 지하2층', 126.886862, 37.4228105, '1544-1122');
insert into CINEMA values (03104, 031, '구리갈매', 6, '경기도 구리시 갈매순환로 180, 힐스테이트 갈매역 스칸센 B동 B1~B2', 127.122664, 37.6355378, '1544-1122');
insert into CINEMA values (03105, 031, '부천역', 8, '경기도 부천시 부일로 460(심곡동, 시네마존 6층~11층)', 126.780991, 37.4858571, '1544-1122');
insert into CINEMA values (03106, 031, '성남모란', 6, '경기도 성남시 중원구 성남대로1136 뉴코아아울렛 9~10층', 127.129746, 37.4313739, '1544-1122');

insert into CINEMA values (03202, 032, '계양점', 8, '인천광역시 계양구 장제로 738, 8층(작전동)', 126.734644, 37.5338297, '1544-1122');
insert into CINEMA values (03203, 032, '부평점', 10, '인천 광역시 부평구 청천동 386번지 아이즈빌아울렛 2층', 126.703601, 37.5217993, '1544-1122');
insert into CINEMA values (03204, 032, '연수역', 10, '인천광역시 연수구 벚꽃로 106 8층', 126.677094, 37.4177089, '1544-1122');
insert into CINEMA values (03205, 032, '인천점', 10, '인천광역시 남동구 예술로 198, 4층(구월동)', 126.701923, 37.4520127, '1544-1122');

insert into CINEMA values (03301, 033, '강릉점', 8, '강원특별자치도 강릉시 옥천동 189 씨네몰 6~8층', 128.898619, 37.7556963, '1544-1122');
insert into CINEMA values (03302, 033, '기린점', 2, '강원특별자치도 인제군 기린면 대내로 34-14 기린체육문화센터 2층', 128.318854, 37.9649355, '1544-1122');
insert into CINEMA values (03303, 033, '원통점', 3, '강원특별자치도 인제군 북면 금강로 11번길 19, 2층', 128.206682, 38.1201419, '1544-1122');
insert into CINEMA values (03304, 033, '인제점', 2, '강원특별자치도 인제군 인제읍 비봉로 44번길 100 1층', 128.165224, 38.0607516, '1544-1122');
insert into CINEMA values (03305, 033, '춘천점', 12, '강원특별자치도 춘천시 지석로 80 (퇴계동, 투탑시티 3층)', 127.743728, 37.8505094, '1544-1122');

insert into CINEMA values (04203, 042, '세종점', 7, '세종특별자치시 도움1로 108 몰리브 7층', 127.247563, 36.5025886, '1544-1122');
insert into CINEMA values (04204, 042, '천안점', 8, '충청남도 천안시 동남구 명동길 47, 5층(대흥동)', 127.148375, 36.8076849, '1544-1122');
insert into CINEMA values (04205, 042, '청주터미널', 9, '충청북도 청주시 흥덕구 2순환로 1233 8층 (가경동)', 127.431227, 36.6261727, '1544-1122');

insert into CINEMA values (05301, 053, '대구점', 7, '대구광역시 북구 침산로 93 스펙트럼시티 4층', 128.589806, 35.8852191, '1544-1122');
insert into CINEMA values (05302, 053, '대구스타디움', 10, '대구광역시 수성구 유니버시아드로 140 (대흥동)', 128.688863, 35.8306656, '1544-1122');
insert into CINEMA values (05303, 053, '대구월성점', 6, '대구광역시 달서구 조암로 29 (월성동) 이래타워 4F', 128.526809, 35.8245685, '1544-1122');

insert into CINEMA values (05102, 051, '서면점', 7, '부산광역시 부산진구 동천로 4, 6층(전포동)', 129.063950, 35.1494282, '1544-1122');
insert into CINEMA values (05103, 051, '울산동구점', 6, '울산광역시 동구 등대로50', 129.430952, 35.4899085, '1544-1122');
insert into CINEMA values (05104, 051, '울산신천점', 6, '울산광역시 북구 매산1로 17 LEEPLEX 6층 (신천동)', 129.355783, 35.6350260, '1544-1122');
insert into CINEMA values (05105, 051, '해운대점', 8, '부산광역시 해운대구 해운대로 620 라뮤에뜨 2층', 129.158501, 35.1630014, '1544-1122');

insert into CINEMA values (05401, 054, '거제점', 7, '경상남도 거제시 장평로 12, 6층 (장평동, 디큐브백화점)', 128.616558, 34.8911623, '1544-1122');
insert into CINEMA values (05402, 054, '구미점', 10, '경상북도 구미시 구미중앙로 44, 4층(원평동)', 128.327701, 36.1300887, '1544-1122');
insert into CINEMA values (05403, 054, '김해점', 9, '경상남도 김해시 내외중앙로 137, 4층(내동)', 128.868586, 35.2421843, '1544-1122');
insert into CINEMA values (05404, 054, '마산점', 9, '경상남도 창원시 마산회원구 3·15대로 736, 8층(합성동)', 128.582381, 35.2375747, '1544-1122');
insert into CINEMA values (05405, 054, '창원점', 6, '경상남도 창원시 의창구 창원대로397번길 6, 8층~11층(팔용동)', 128.642011, 35.2346180, '1544-1122');

insert into CINEMA values (06201, 062, '광주금남로', 9, '광주광역시 동구 중앙로 160번길 16-7 부로타워 2층', 126.914690, 35.1469101, '1544-1122');
insert into CINEMA values (06202, 062, '광주첨단점', 9, '광주 광산구 임방울대로826번길 29-31 2층', 126.850364, 35.2164672, '1544-1122');
insert into CINEMA values (06203, 062, '광주터미널', 11, '광주광역시 서구 무진대로 904, 2층~6층(광천동)', 126.878697, 35.1603362, '1544-1122');
insert into CINEMA values (06204, 062, '목포점', 5, '전라남도 목포시 교육로 63 (상동)', 126.419894, 34.8025037, '1544-1122');
insert into CINEMA values (06205, 062, '전주고사점', 10, '전라북도 전주시 완산구 전주객사3길 72(고사동)', 127.142306, 35.8202422, '1544-1122');
insert into CINEMA values (06206, 062, '전주효자점', 9, '전라북도 전주시 완산구 효자동 1가 434 Mall of Hyoja 2층', 127.115585, 35.8069230, '1544-1122');
insert into CINEMA values (06207, 062, '제주점', 8, '제주특별자치도 제주시 서광로 288, 3층~7층(이도2동)', 126.527393, 33.5000977, '1544-1122');

--
-- member
insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
values(seq_member_id.nextVal, 'rhgPwls','$2a$10$9B5JXlO9iyWBdFrRc0u5C.T1gyOAdVNM.7RFKU1Kf9jM69HfzbrzK','update_set@naver.com','고혜진','01012341234','1990') ;
insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
values(seq_member_id.nextVal, 'testUser','$2a$10$ht3O5/jiFv31l6fOXMCFaeq.qc1V66N5Ok2XJ0LYV1Dq4XKwVPCAi','testUser1234@naver.com','김테스트','01012345678','1993') ;
insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
values(seq_member_id.nextVal, 'sinsa','$2a$10$uZXJvi3TRlnYlWXa8RXlhu1MZe7A8lWFlyolaBCcukPygChuy6pge','sinsa@naver.com','신사임당','01011112222','1996');
insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
values(seq_member_id.nextVal, 'honggd','$2a$10$.jBRzR3RXRO0iXRS0lVtX.rdXH6H/.pDUzV6.Bs93pMToywUaeT/O','honggd@naver.com','홍길동','01022221111','2000');
insert into MEMBER (id,member_login_id,member_pwd,member_email,member_name,member_phone,birthyear)
values(seq_member_id.nextVal, 'less','$2a$10$WqWrsGjPJEVkLV8TCCiIe.EPTEZrCE7iVNRFA7DkNnxnBrSFHiSPm','leess@naver.com','이순신','01013225521','1989');
--
-- admin
insert into ADMIN values (seq_admin_id.nextval, 0201, 'adm220932', '$2a$10$uMDn8AayoMTQxT9BpXqPKuD04epPWQZQZDYVfj6moSOa.PmO5I1SS');
insert into ADMIN values (seq_admin_id.nextval, 03101, 'adm650704', '$2a$10$9RItpz0gE.7jj.nEPr6haOuIqnOh7K/M..PeAy9qfviXKMIhb0mi6');
insert into Admin values (seq_admin_id.nextval, 03201, 'adm392258', '$2a$10$zqEP3U9PTAFjyNTd7bgldeBmCBcSVIeZbIw/uLhhRtV8gg2RutChO');
insert into ADMIN values (seq_admin_id.nextval, 04201, 'adm852542', '$2a$10$uOpP/wdt8/bkTqXlKqu3JeqyIDqvnD3sF5S8xHEYHBxKYvy80HCvW');
insert into ADMIN values (seq_admin_id.nextval, 05101, 'adm724198', '$2a$10$taKBaHUBRTNKEAUq.zu1lOTX2uvuoUqLnJvJRAaTKcUJbzNJOZtxm');
insert into ADMIN values (seq_admin_id.nextval, null, 'adm322100', '$2a$10$Ix/RI6AiRdQEhOHd3nrQX.PxOua2R5kFYZxt67hmBiamIPqFfLhi2');

--
-- authority
insert into AUTHORITY values (seq_authority_id.nextval, 1, null, 'ROLE_USER');
insert into AUTHORITY values (seq_authority_id.nextval, 2, null, 'ROLE_USER');
insert into AUTHORITY values (seq_authority_id.nextval, 3, null, 'ROLE_USER');
insert into AUTHORITY values (seq_authority_id.nextval, 4, null, 'ROLE_USER');
insert into AUTHORITY values (seq_authority_id.nextval, 5, null, 'ROLE_USER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 1, 'ROLE_MANAGER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 2, 'ROLE_MANAGER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 3, 'ROLE_MANAGER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 4, 'ROLE_MANAGER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 5, 'ROLE_MANAGER');
insert into AUTHORITY values (seq_authority_id.nextval, null, 6, 'ROLE_ADMIN');
-- theater
insert into THEATER values (020101, 0201, '1관', 60);

insert into THEATER values (020201, 0202, '1관', 60);
insert into THEATER values (020202, 0202, '2관', 60);
insert into THEATER values (020203, 0202, '3관', 60);
insert into THEATER values (020204, 0202, '4관', 60);

insert into THEATER values (020301, 0203, '1관', 60);
insert into THEATER values (020302, 0203, '2관', 60);
insert into THEATER values (020303, 0203, '3관', 60);
insert into THEATER values (020304, 0203, '4관', 60);

insert into THEATER values (020401, 0204, '1관', 60);
insert into THEATER values (020402, 0204, '2관', 60);
insert into THEATER values (020403, 0204, '3관', 60);
insert into THEATER values (020404, 0204, '4관', 60);

insert into THEATER values (020501, 0205, '1관', 60);
insert into THEATER values (020502, 0205, '2관', 60);
insert into THEATER values (020503, 0205, '3관', 60);
insert into THEATER values (020504, 0205, '4관', 60);

insert into THEATER values (020601, 0206, '1관', 60);
insert into THEATER values (020602, 0206, '2관', 60);
insert into THEATER values (020603, 0206, '3관', 60);
insert into THEATER values (020604, 0206, '4관', 60);

insert into THEATER values (020701, 0207, '1관', 60);
insert into THEATER values (020702, 0207, '2관', 60);
insert into THEATER values (020703, 0207, '3관', 60);
insert into THEATER values (020704, 0207, '4관', 60);

insert into THEATER values (020801, 0208, '1관', 60);
insert into THEATER values (020802, 0208, '2관', 60);
insert into THEATER values (020803, 0208, '3관', 60);
insert into THEATER values (020804, 0208, '4관', 60);

insert into THEATER values (020901, 0209, '1관', 60);
insert into THEATER values (020902, 0209, '2관', 60);
insert into THEATER values (020903, 0209, '3관', 60);
insert into THEATER values (020904, 0209, '4관', 60);

insert into THEATER values (021001, 0210, '1관', 60);
insert into THEATER values (021002, 0210, '2관', 60);
insert into THEATER values (021003, 0210, '3관', 60);
insert into THEATER values (021004, 0210, '4관', 60);

insert into THEATER values (0310101, 03101, '1관', 60);
insert into THEATER values (0310102, 03101, '2관', 60);
insert into THEATER values (0310103, 03101, '3관', 60);
insert into THEATER values (0310104, 03101, '4관', 60);

insert into THEATER values (0310201, 03102, '1관', 60);
insert into THEATER values (0310202, 03102, '2관', 60);
insert into THEATER values (0310203, 03102, '3관', 60);
insert into THEATER values (0310204, 03102, '4관', 60);

insert into THEATER values (0310301, 03103, '1관', 60);
insert into THEATER values (0310302, 03103, '2관', 60);
insert into THEATER values (0310303, 03103, '3관', 60);
insert into THEATER values (0310304, 03103, '4관', 60);

insert into THEATER values (0310401, 03104, '1관', 60);
insert into THEATER values (0310402, 03104, '2관', 60);
insert into THEATER values (0310403, 03104, '3관', 60);
insert into THEATER values (0310404, 03104, '4관', 60);

insert into THEATER values (0310501, 03105, '1관', 60);
insert into THEATER values (0310502, 03105, '2관', 60);
insert into THEATER values (0310503, 03105, '3관', 60);
insert into THEATER values (0310504, 03105, '4관', 60);

insert into THEATER values (0310601, 03106, '1관', 60);
insert into THEATER values (0310602, 03106, '2관', 60);
insert into THEATER values (0310603, 03106, '3관', 60);
insert into THEATER values (0310604, 03106, '4관', 60);

insert into THEATER values (0320101, 03201, '1관', 60);
insert into THEATER values (0320102, 03201, '2관', 60);
insert into THEATER values (0320103, 03201, '3관', 60);
insert into THEATER values (0320104, 03201, '4관', 60);

insert into THEATER values (0320201, 03202, '1관', 60);
insert into THEATER values (0320202, 03202, '2관', 60);
insert into THEATER values (0320203, 03202, '3관', 60);
insert into THEATER values (0320204, 03202, '4관', 60);

insert into THEATER values (0320301, 03203, '1관', 60);
insert into THEATER values (0320302, 03203, '2관', 60);
insert into THEATER values (0320303, 03203, '3관', 60);
insert into THEATER values (0320304, 03203, '4관', 60);

insert into THEATER values (0320401, 03204, '1관', 60);
insert into THEATER values (0320402, 03204, '2관', 60);
insert into THEATER values (0320403, 03204, '3관', 60);
insert into THEATER values (0320404, 03204, '4관', 60);

insert into THEATER values (0320501, 03205, '1관', 60);
insert into THEATER values (0320502, 03205, '2관', 60);
insert into THEATER values (0320503, 03205, '3관', 60);
insert into THEATER values (0320504, 03205, '4관', 60);

insert into THEATER values (0330101, 03301, '1관', 60);
insert into THEATER values (0330102, 03301, '2관', 60);
insert into THEATER values (0330103, 03301, '3관', 60);
insert into THEATER values (0330104, 03301, '4관', 60);

insert into THEATER values (0330201, 03302, '1관', 60);
insert into THEATER values (0330202, 03302, '2관', 60);
insert into THEATER values (0330203, 03302, '3관', 60);
insert into THEATER values (0330204, 03302, '4관', 60);

insert into THEATER values (0330301, 03303, '1관', 60);
insert into THEATER values (0330302, 03303, '2관', 60);
insert into THEATER values (0330303, 03303, '3관', 60);
insert into THEATER values (0330304, 03303, '4관', 60);

insert into THEATER values (0330401, 03304, '1관', 60);
insert into THEATER values (0330402, 03304, '2관', 60);
insert into THEATER values (0330403, 03304, '3관', 60);
insert into THEATER values (0330404, 03304, '4관', 60);

insert into THEATER values (0330501, 03305, '1관', 60);
insert into THEATER values (0330502, 03305, '2관', 60);
insert into THEATER values (0330503, 03305, '3관', 60);
insert into THEATER values (0330504, 03305, '4관', 60);

insert into THEATER values (0420101, 04201, '1관', 60);
insert into THEATER values (0420102, 04201, '2관', 60);
insert into THEATER values (0420103, 04201, '3관', 60);
insert into THEATER values (0420104, 04201, '4관', 60);

insert into THEATER values (0420201, 04202, '1관', 60);
insert into THEATER values (0420202, 04202, '2관', 60);
insert into THEATER values (0420203, 04202, '3관', 60);
insert into THEATER values (0420204, 04202, '4관', 60);

insert into THEATER values (0420301, 04203, '1관', 60);
insert into THEATER values (0420302, 04203, '2관', 60);
insert into THEATER values (0420303, 04203, '3관', 60);
insert into THEATER values (0420304, 04203, '4관', 60);

insert into THEATER values (0420401, 04204, '1관', 60);
insert into THEATER values (0420402, 04204, '2관', 60);
insert into THEATER values (0420403, 04204, '3관', 60);
insert into THEATER values (0420404, 04204, '4관', 60);

insert into THEATER values (0420501, 04205, '1관', 60);
insert into THEATER values (0420502, 04205, '2관', 60);
insert into THEATER values (0420503, 04205, '3관', 60);
insert into THEATER values (0420504, 04205, '4관', 60);

insert into THEATER values (0530101, 05301, '1관', 60);
insert into THEATER values (0530102, 05301, '2관', 60);
insert into THEATER values (0530103, 05301, '3관', 60);
insert into THEATER values (0530104, 05301, '4관', 60);

insert into THEATER values (0530201, 05302, '1관', 60);
insert into THEATER values (0530202, 05302, '2관', 60);
insert into THEATER values (0530203, 05302, '3관', 60);
insert into THEATER values (0530204, 05302, '4관', 60);

insert into THEATER values (0530301, 05303, '1관', 60);
insert into THEATER values (0530302, 05303, '2관', 60);
insert into THEATER values (0530303, 05303, '3관', 60);
insert into THEATER values (0530304, 05303, '4관', 60);

insert into THEATER values (0510101, 05101, '1관', 60);
insert into THEATER values (0510102, 05101, '2관', 60);
insert into THEATER values (0510103, 05101, '3관', 60);
insert into THEATER values (0510104, 05101, '4관', 60);

insert into THEATER values (0510201, 05102, '1관', 60);
insert into THEATER values (0510202, 05102, '2관', 60);
insert into THEATER values (0510203, 05102, '3관', 60);
insert into THEATER values (0510204, 05102, '4관', 60);

insert into THEATER values (0510301, 05103, '1관', 60);
insert into THEATER values (0510302, 05103, '2관', 60);
insert into THEATER values (0510303, 05103, '3관', 60);
insert into THEATER values (0510304, 05103, '4관', 60);

insert into THEATER values (0510401, 05104, '1관', 60);
insert into THEATER values (0510402, 05104, '2관', 60);
insert into THEATER values (0510403, 05104, '3관', 60);
insert into THEATER values (0510404, 05104, '4관', 60);

insert into THEATER values (0510501, 05105, '1관', 60);
insert into THEATER values (0510502, 05105, '2관', 60);
insert into THEATER values (0510503, 05105, '3관', 60);
insert into THEATER values (0510504, 05105, '4관', 60);

insert into THEATER values (0540101, 05401, '1관', 60);
insert into THEATER values (0540102, 05401, '2관', 60);
insert into THEATER values (0540103, 05401, '3관', 60);
insert into THEATER values (0540104, 05401, '4관', 60);

insert into THEATER values (0540201, 05402, '1관', 60);
insert into THEATER values (0540202, 05402, '2관', 60);
insert into THEATER values (0540203, 05402, '3관', 60);
insert into THEATER values (0540204, 05402, '4관', 60);

insert into THEATER values (0540301, 05403, '1관', 60);
insert into THEATER values (0540302, 05403, '2관', 60);
insert into THEATER values (0540303, 05403, '3관', 60);
insert into THEATER values (0540304, 05403, '4관', 60);

insert into THEATER values (0540401, 05404, '1관', 60);
insert into THEATER values (0540402, 05404, '2관', 60);
insert into THEATER values (0540403, 05404, '3관', 60);
insert into THEATER values (0540404, 05404, '4관', 60);

insert into THEATER values (0540501, 05405, '1관', 60);
insert into THEATER values (0540502, 05405, '2관', 60);
insert into THEATER values (0540503, 05405, '3관', 60);
insert into THEATER values (0540504, 05405, '4관', 60);

insert into THEATER values (0620101, 06201, '1관', 60);
insert into THEATER values (0620102, 06201, '2관', 60);
insert into THEATER values (0620103, 06201, '3관', 60);
insert into THEATER values (0620104, 06201, '4관', 60);

insert into THEATER values (0620201, 06202, '1관', 60);
insert into THEATER values (0620202, 06202, '2관', 60);
insert into THEATER values (0620203, 06202, '3관', 60);
insert into THEATER values (0620204, 06202, '4관', 60);

insert into THEATER values (0620301, 06203, '1관', 60);
insert into THEATER values (0620302, 06203, '2관', 60);
insert into THEATER values (0620303, 06203, '3관', 60);
insert into THEATER values (0620304, 06203, '4관', 60);

insert into THEATER values (0620401, 06204, '1관', 60);
insert into THEATER values (0620402, 06204, '2관', 60);
insert into THEATER values (0620403, 06204, '3관', 60);
insert into THEATER values (0620404, 06204, '4관', 60);

insert into THEATER values (0620501, 06205, '1관', 60);
insert into THEATER values (0620502, 06205, '2관', 60);
insert into THEATER values (0620503, 06205, '3관', 60);
insert into THEATER values (0620504, 06205, '4관', 60);

insert into THEATER values (0620601, 06206, '1관', 60);
insert into THEATER values (0620602, 06206, '2관', 60);
insert into THEATER values (0620603, 06206, '3관', 60);
insert into THEATER values (0620604, 06206, '4관', 60);

insert into THEATER values (0620701, 06207, '1관', 60);
insert into THEATER values (0620702, 06207, '2관', 60);
insert into THEATER values (0620703, 06207, '3관', 60);
insert into THEATER values (0620704, 06207, '4관', 60);
-- 강남점 상영관 추가
insert into THEATER values (020102, 0201, '2관', 60);
insert into THEATER values (020103, 0201, '3관', 60);
insert into THEATER values (020104, 0201, '4관', 60);


-- genreData
-- insert into GENRE values (seq_genre_id.nextval, '드라마');
-- insert into GENRE values (seq_genre_id.nextval, '액션');
-- insert into GENRE values (seq_genre_id.nextval, '코미디');
-- insert into GENRE values (seq_genre_id.nextval, '판타지');
-- insert into GENRE values (seq_genre_id.nextval, '범죄');
-- insert into GENRE values (seq_genre_id.nextval, '멜로');
-- insert into GENRE values (seq_genre_id.nextval, '느와르');
-- insert into GENRE values (seq_genre_id.nextval, '스릴러');
-- insert into GENRE values (seq_genre_id.nextval, '공포');
-- insert into GENRE values (seq_genre_id.nextval, '전쟁');
-- insert into GENRE values (seq_genre_id.nextval, '애니메이션');
-- insert into GENRE values (seq_genre_id.nextval, '로맨스');
-- insert into GENRE values (seq_genre_id.nextval, '호러');
--
-- movieData
--insert into
--   movieData
--values (1, '웡카', 'ALL', '2024.01.31', 116,
--       'http://h.vod.cgv.co.kr/vodCGVa/87937/87937_222373_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87937/87937_1000.jpg',
--       '폴 킹', '티모시 샬라메, 칼라 레인, 올리비아 콜맨, 톰 데이비스', '세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다! 마법사이자 초콜릿 메이커 ‘윌리 웡카’의 꿈은 디저트의 성지, ‘달콤 백화점’에 자신만의 초콜릿 가게를 여는 것.
--  가진 것이라고는 낡은 모자 가득한 꿈과 단돈 12소버린 뿐이지만 특별한 마법의 초콜릿으로 사람들을 사로잡을 자신이 있다.
--  하지만 먹을 것도, 잠잘 곳도, 의지할 사람도 없는 상황 속에서 낡은 여인숙에 머물게 된 ‘웡카’는
--  ‘스크러빗 부인’과 ‘블리처’의 계략에 빠져 눈더미처럼 불어난 숙박비로 인해 순식간에 빚더미에 오른다.
--  게다가 밤마다 초콜릿을 훔쳐가는 작은 도둑 ‘움파 룸파’의 등장과 ‘달콤 백화점’을 독점한 초콜릿 카르텔의 강력한 견제까지.
--  세계 최고의 초콜릿 메이커가 되는 길은 험난하기만 한데…',
--       17.3);
--insert into
--   movieData
--values (2, '시민덕희', 'FIFTEEN', '2024.01.24', 114,
--       'http://h.vod.cgv.co.kr/vodCGVa/87866/87866_222813_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87866/87866_1000.jpg',
--       '박영주', '라미란, 공명, 염혜란, 안은진', '내 돈을 사기 친 그 놈이 구조 요청을 해왔다! 세탁소 화재로 인해 대출상품을 알아보던 생활력 만렙 덕희에게 어느 날, 거래은행의 손대리가 합리적인 대출상품을 제안하겠다며 전화를 걸어온다.
--  대출에 필요하다며 이런저런 수수료를 요구한 손대리에게 돈을 보낸 덕희는 이 모든 과정이 보이스피싱이었음을 뒤늦게 인지하고 충격에 빠진다.
--  전 재산을 잃고 아이들과 거리로 나앉게 생긴 덕희에게 어느 날 손대리가 다시 전화를 걸어오는데… 이번엔 살려달라는 전화다!
--  경찰도 포기한 사건, 덕희는 손대리도 구출하고 잃어버린 돈도 찾겠다는 일념으로
--  필살기 하나씩 장착한 직장 동료들과 함께 중국 칭다오로 직접 날아간다.',
--       5.9);
--insert into
--   movieData
--values (3, '도그데이즈', 'TWELVE', '2024.02.07', 120,
--       'http://h.vod.cgv.co.kr/vodCGVa/87978/87978_222744_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87978/87978_1000.jpg',
--       '김덕민', '윤여정, 유혜진, 김윤진, 김서형', '깔끔한 성격의 계획형 싱글남 ‘민상’(유해진).영끌까지 모아 산 건물을 개똥밭으로 만드는 세입자 수의사 ‘진영’(김서형) 때문에 매일 머리가 아프다.
--   오늘도‘진영’과 티격태격하던 ‘민상’은 동물병원에서 한 성격하는 할머니를 만나는데...',
--       8.3);
--insert into
--   movieData
--values (4, '데드맨', 'FIFTEEN', '2024.02.07', 108,
--       'http://h.vod.cgv.co.kr/vodCGVa/87981/87981_222534_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87981/87981_1000.jpg',
--       '하준원', '조진웅, 김희애, 이수경', '목숨값 단돈 500만원! 이름값 1000억? 이름에 살고, 이름에 죽는다! 살아있지만 죽은 사람, 즉 ‘데드맨’이 되어 영문도 모른 채 중국의 사설감옥에 끌려간 ‘이만재’.
--  정치 컨설턴트 ‘심여사’가 그의 앞에 나타나 목숨값을 담보로 위험한 제안을 하고,
--  ‘이만재’ 때문에 아버지가 죽었다고 주장하는 ‘공희주’가 등장하면서
--  1천억짜리 설계판의 배후를 찾기 위해 의기투합한 세 사람의 추적이 시작되는데…',
--       4.0);
--insert into
--   movieData
--values (5, '아가일', 'TWELVE', '2024.02.07', 139,
--       'http://h.vod.cgv.co.kr/vodCGVa/87426/87426_220043_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87426/87426_1000.jpg',
--       '매튜 본', '헨리 카빌, 브라이스 달라스 하워드, 샘 록웰, 두아 리파', '내가 쓴 베스트셀러 스파이 소설이 현실이 되었습니다?! 현실감 넘치는 스파이 세계를 구현한 책 ‘아가일’로 엄청난 성공을 거둔 베스트셀러 작가 ‘엘리’.
--  소설의 마지막 권을 앞둔 그녀는 자기도 모르는 사이 수많은 적들에게 둘러 쌓이고 그녀 앞에 갑자기 추레한 행색의 현실 스파이 ‘에이든’이 나타나 그녀를 구해준다.
--  그는 그녀의 소설 ‘아가일’ 속 사건이 현실이 되었고 그로 인해 엘리가 전세계 스파이들이 표적이 되었다고 말한다.
--  자신을 쫓는 전세계의 스파이들로부터 벗어나기 위해, 엘리는 소설의 다음 챕터를 쓰고 그 안의 단서를 바탕으로 현실의 레전드 요원 아가일을 찾아야만 한다!',
--       3.0);
--insert into
--   movieData
--values (6, '소풍', 'TWELVE', '2024.02.07', 114,
--       'http://h.vod.cgv.co.kr/vodCGVa/87999/87999_222674_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87999/87999_1000.jpg',
--       '김용균', '나문희, 김영옥, 박근형, 류승수',
--       '60년 만에 찾아간 고향, 16살의 추억을 만났다. 요즘 들어 돌아가신 엄마가 자꾸 꿈에 보이는 은심(나문희). 마침 절친이자 사돈 지간인 금순(김영옥)이 연락도 없이 불쑥 찾아오자, 은심은 금순과 함께 고향 남해로 떠나기로 한다. 그곳에서 우연히 자신을 짝사랑하던 태호(박근형)를 만나며 잊고 지낸 추억을 하나둘씩 떠올리게 되는데…“다음에 다시 태어나도 네 친구 할 끼야” 한 편의 시가 되는 우정, 어쩌면 마지막 소풍이 시작된다.',
--       3.5);
--insert into
--   movieData
--values (7, '아기상어 극장판-사이렌 스톤의 비밀', 'ALL', '2024.02.07', 84,
--       'http://h.vod.cgv.co.kr/vodCGVa/87993/87993_222614_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87993/87993_1000.jpg',
--       '알란 포맨', '장예나, 전태열, 쓰복만, 씨엘', '대도시 미끈매끈 시티로 이사한 아기상어 ‘올리’! 최고의 단짝 ‘윌리엄’과 헤어져야 한다는 슬픔도 잠시, 뉴욕처럼 반짝이는 화려한 풍경과 멋진 음악에 설렌다. 벨루가 아이돌 ‘엔하이픈’, 상어 팝스타 ‘샤키L’, 그리고 최고의 스타 불가사리 ‘스타리아나’까지! 어느 날 ‘올리’는 ‘스타리아나’의 인기 비결,
--  사이렌 스톤’의 숨겨진 비밀과 스타리아나’의 거대한 음모를 알게 되는데… 아기상어, 스톤의 저주에 맞서 바다를 지켜라!',
--       2.4);
--insert into
--   movieData
--values (8, '정글번치-월드투어', 'ALL', '2024.01.31', 89,
--       'http://h.vod.cgv.co.kr/vodCGVa/87972/87972_222612_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87972/87972_1000.jpg',
--       '로랑 브루', '석승훈, 정해은, 장병관, 박시윤', '정글에 부글부글 거품 폭탄이?! 두더지 악당의 습격으로 폭발 위기에 처한 정글! 정글을 구해야만 해! 정글을 구하려면
--    전설의 ‘알버트’ 박사를 찾아서 해독제를 만들어야 해! 눈보라 산, 사막, 비밀 동굴,
--    대나무 숲까지 전 세계로 떠난 정글번치! 과연 두더지 악당의 추격을 피해 박사를 찾고 정글을 구할 수 있을까? 올 겨울방학, 정글 구하는 김에 세계일주 가자고!!',
--       1.6);
--insert into
--   movieData
--values (9, '추락의 해부', 'FIFTEEN', '2024.01.31', 152,
--       'http://h.vod.cgv.co.kr/vodCGVa/87979/87979_222625_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87979/87979_1000.jpg',
--       '쥐스틴 트리에', '산드라 휠러, 스완 아를로, 밀로 마차도 그라너', '남편의 추락사로 한순간에 유력한 용의자로 지목된 유명 작가 ‘산드라’. 유일한 목격자는 시각장애가 있는 아들과 안내견뿐. 단순한 사고였을까? 아니면 우발적 자살 혹은 의도된 살인? 사건의 전말을 해부해 가는 제76회 칸영화제 황금종려상 수상작',
--       1.0);
--insert into
--   movieData
--values (10, '서울의 봄', 'TWELVE', '2023.11.22', 141,
--       'http://h.vod.cgv.co.kr/vodCGVa/87554/87554_220955_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87554/87554_1000.jpg',
--       '김성수', '황정민, 정우성, 이성민, 박해준', '1979년 12월 12일, 수도 서울 군사반란 발생 그날, 대한민국의 운명이 바뀌었다. 대한민국을 뒤흔든 10월 26일 이후,
--    서울에 새로운 바람이 불어온 것도 잠시 12월 12일, 보안사령관 전두광이 반란을 일으키고 군 내 사조직을 총동원하여 최전선의 전방부대까지 서울로 불러들인다. 권력에 눈이 먼 전두광의 반란군과 이에 맞선 수도경비사령관 이태신을 비롯한 진압군 사이, 일촉즉발의 9시간이 흘러가는데… 목숨을 건 두 세력의 팽팽한 대립 오늘 밤, 대한민국 수도에서 가장 치열한 전쟁이 펼쳐진다!',
--       0.4);
--insert into
--   movieData
--values (11, '아네모네 ', 'FIFTEEN', '2024.02.27', 75,
--       'http://h.vod.cgv.co.kr/vodCGVa/87998/87998_222685_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87998/87998_1000.jpg',
--       '정하용', '정이랑, 박성진', '오늘 하루도 열심히 밥벌이한 집안의 가장 ‘용자’가 오늘 하루도 역시나 밥만 축낸 백수 남편 ‘성진’에게 오늘 하루만 오로지 부탁한 심부름이 있다. 그리하여 오늘 용자가 성진에게 묻고 싶은 단 한 마디 “로또 샀어, 안 샀어?” 1등 당첨 로또를 쟁취하기 위한 용자들의 필사의 레이스가 시작된다!',
--       0.4);
--insert into
--   movieData
--values (12, '신차원! 짱구는 못말려 더 무비 초능력 대결전 ~날아라 수제김밥~', 'ALL', '2023.12.22', 94,
--       'http://h.vod.cgv.co.kr/vodCGVa/87888/87888_221575_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87888/87888_1000.jpg',
--       '요네 히토시', '박영남, 강희선, 김환진', '최초의 3D CG! 제작 기간 7년 최고의 웃음과 감동! 최강의 스케일 옷까지 갈아입은 볼록 짱구 등장! 어느 날, 우주에서 날아온 검은 빛과 하얀 빛이 떡잎마을을 향해 떨어진다. 평소처럼 저녁밥을 손꼽아 기다리던 짱구는 하얀 빛에 정통으로 맞게 되고 그러자 온몸에 넘치는 신비한 힘! 힘에 몸을 맡긴 채 엉덩이에 의식을 집중하자 장난감들이 붕붕 떠오른다.
--"엉덩이... 엉덩이가 뜨거워...!? 뭔지 몰라도 엄청난 힘을 손에 넣은 것 같아." 한편, 검은 빛을 통해 초능력을 손에 넣은 또 다른 남자는 이 세상의 파멸을 바라며 폭주하기 시작하는데, 위기에 처한 세상을 구하기 위한 유일한 희망이 바로 짱구...!? 올 겨울, 짱구의 엉덩이가 뜨겁게 타오른다!',
--       0.1);
--insert into
--   movieData
--values (13, '킹덤-엑소더스', 'EIGHTEEN', '2024.01.31', 317,
--       'http://h.vod.cgv.co.kr/vodCGVa/88016/88016_222917_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000088/88016/88016_1000.jpg',
--       '라스 폰 트리에', '보딜 요르겐센, 미카엘 페르스브란트, 라스 미켈센, 니콜라스 브로', '“선도 악도 있음을 명심하라” 코펜하겐 최고의 종합병원 ‘킹덤’. 음산하고 기묘한 기운이 감도는 어느 날 밤, 몽유병자 카렌이 알 수 없는 힘에 이끌려 ‘킹덤’ 앞에 도착한다. 사악한 악으로부터 ‘킹덤’을 구하기 위해 25년간 풀지 못한 비밀을 찾기 시작하는데… ‘킹덤’의 입구가 다시 열리고 숨어있던 절대 악이 부활한다!',
--       0.1);
--insert into
--   movieData
--values (14, '상견니', 'FIFTEEN', '2024.01.25', 107,
--       'http://h.vod.cgv.co.kr/vodCGVa/86750/86750_211203_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000086/86750/86750_1000.jpg',
--       '황천인', '가가연, 허광한, 시백우', '드라마 스토리를 영화 버전으로 확장시킨 멀티버스 판타지 로맨스 완전히 새로운 세계관, 완전히 새로운 스토리의 <상견니> 2009년, 리쯔웨이와 황위쉬안은 밀크티 가게에서 우연히 재회한다. 처음 만났지만 마치 오래전부터 알고 있었던 것 같은 기시감과 묘한 설렘을 느끼는 두 사람. 이들은 사계절을 함께 보내며 가까워지고, 2010년의 마지막 날, 함께 새해를 맞이하며 연인이 된다. 2017년, 황위쉬안의 인생에 예상치 못한 변화가 생긴다. 해외 발령을 받게 된 것. 황위쉬안은 이 제안을 받아들이고 새로운 여정을 시작하지만 이 선택은 그녀의 미래를 바꿀 뿐만 아니라, 리쯔웨이와 모쥔제, 그리고 그녀가 아직 모르는 천윈루의 운명까지 뒤바꿔 놓는데… 이제, 이들은 수없이 뒤엉킨 타임라인 속에서 서로를 구하기 위해 낡은 테이프 속 들려오는 노래 ‘라스트 댄스’를 따라 달려가기 시작한다.',
--       0.2);
--insert into
--   movieData
--values (15, '파묘', 'FIFTEEN', '2024.02.22', 134,
--       'http://h.vod.cgv.co.kr/vodCGVa/88012/88012_222840_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000088/88012/88012_1000.jpg',
--       '장재현', '최민식, 김고은, 유해진 , 이도현', '미국 LA, 거액의 의뢰를 받은 무당 ‘화림’(김고은)과 ‘봉길’(이도현)은 기이한 병이 대물림되는 집안의 장손을 만난다. 조상의 묫자리가 화근임을 알아챈 ‘화림’은 이장을 권하고, 돈 냄새를 맡은 최고의 풍수사 ‘상덕’(최민식)과 장의사 ‘영근’(유해진)이 합류한다. “전부 잘 알 거야… 묘 하나 잘못 건들면 어떻게 되는지” 절대 사람이 묻힐 수 없는 악지에 자리한 기이한 묘. ‘상덕’은 불길한 기운을 느끼고 제안을 거절하지만, ‘화림’의 설득으로 결국 파묘가 시작되고… 나와서는 안될 것이 나왔다.',
--       42.0);
--insert into
--   movieData
--values (16, '귀멸의 칼날-인연의 기적, 그리고 합동 강화 훈련으로', 'FIFTEEN', '2024.02.14', 103,
--       'http://h.vod.cgv.co.kr/vodCGVa/87985/87985_222749_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87985/87985_1000.jpg',
--       '소토자키 하루오', '하나에 나츠키, 키토 아카리, 하나자와 카나, 카와니시 켄고', null,
--       2.8);
--insert into
--   movieData
--values (17, '사운드 오브 프리덤', 'FIFTEEN', '2024.02.21', 131,
--       'http://h.vod.cgv.co.kr/vodCGVa/88000/88000_222754_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000088/88000/88000_1000.jpg',
--       '알레한드로 고메즈 몬테베르드', '제임스 카비젤', '인신매매시장 규모 연 1,500억불 전 세계 800만 명의 아이들이 사라졌다! 한 순간에 납치되어, 전 세계에 밀매되는 아이들. 믿을 수 없는 실화를 기반으로 한 구출 작전이 시작된다!',
--       1.7);
--insert into
--   movieData
--values (18, '오펜하이머', 'FIFTEEN', '2023.08.15', 180,
--       'http://h.vod.cgv.co.kr/vodCGVa/87175/87175_216496_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87175/87175_1000.jpg',
--       '크리스토퍼 놀란', '킬리언 머피, 에밀리 블런트, 맷 데이먼, 로버트 다우니 주니어', '“나는 이제 죽음이요, 세상의 파괴자가 되었다.” 세상을 구하기 위해 세상을 파괴할 지도 모르는 선택을 해야 하는 천재 과학자의 핵개발 프로젝트.',
--       0.6);
--insert into
--   movieData
--values (19, '바튼 아카데미', 'FIFTEEN', '2024.02.21', 133,
--       'http://h.vod.cgv.co.kr/vodCGVa/88001/88001_222912_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000088/88001/88001_1000.jpg',
--       '알렉산더 페인', '폴 지아마티, 더바인 조이 랜돌프, 도미닉 세사', '함께 있지만 그들은 언제나 혼자였다 1970년 바튼 아카데미, 크리스마스를 맞아 모두가 떠난 텅빈 학교에는 세 사람이 남게 된다. 고집불통 역사 선생님 ‘폴’, 문제아 ‘털리’ 그리고 주방장 ‘메리’ 이들은 원치 않았던 동고동락을 시작하게 되고, 예상치 못한 순간, 서로의 비밀을 공유하면서 특별한 우정을 나누게 되는데…',
--       0.6);
--insert into
--   movieData
--values (20, '스노우 퍼핀즈', 'ALL', '2024.02.15', 70,
--       'http://h.vod.cgv.co.kr/vodCGVa/87980/87980_222838_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87980/87980_1000.jpg',
--       '네스토르 F. 데니스', '조니 뎁, 김대중', '슈퍼 버드 히어로 퍼핀즈와 함께라면 매일매일이 신나는 모험이 된다! 똘똘이 퍼핀 ‘택’ X 장꾸 삐삐 퍼핀 ‘디디’ X 튼튼이 메가 퍼핀 ‘파이’ X 순둥이 막내 퍼핀 ‘틱’ 그리고 퍼핀즈의 든든한 리더 ‘조니 퍼프’ 그들은 우연히 외계 운석을 찾고 각기 다른 슈퍼파워를 갖게 된다. 그때 북극을 파괴하는 욕심쟁이 슈퍼 악당 ‘오토’가 나타나는데... 스노우 타운과 북극을 지키기 위한 퍼핀즈의 시크릿 미션이 시작된다!',
--       0.4);
--
-- movie_genre
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 4, 1);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 1);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 2);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 3);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 5, 4);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 2, 5);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 5);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 6);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 7);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 8);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 9);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 9);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 11);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 11);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 2, 11);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 12);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 13, 13);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 13);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 14);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 4, 14);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 12, 14);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 6, 14);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 15);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 16);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 5, 17);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 18);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 18);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 19);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 19);
--insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 20);
--
-- member_like_genre
--insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 1, 1);
--insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 2, 1);
--insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 3, 4);
--insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 4, 3);
--insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 5, 2);
--
-- member_like_cinema
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 1, 0201);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 2, 0201);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 3, 03101);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 4, 04201);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 5, 03101);
--
-- ask
insert into ASK values (seq_ask_id.nextval, '1', '예매 취소 및 환불', '예매 취소 및 환불 규정은 어떻게 되나요?', 'RESERVATION', default);
insert into ASK values (seq_ask_id.nextval, '2', '영화 시간보다 늦었어요.', ' 영화 시간보다 늦었어요. 입장 가능한가요?', 'MOVIE', default);
insert into ASK values (seq_ask_id.nextval, '3', '음식물 반입', '상영관 내 다른 음식물의 반입이 되나요?', 'MOVIE', default);
insert into ASK values (seq_ask_id.nextval, '4', '관람 등급', '관람 등급에 대해 알고 싶습니다.', 'MOVIE', default);
insert into ASK values (seq_ask_id.nextval, '5', '영화 관람을 하다 소지품을 분실했어요', '영화 관람을 하다 소지품을 분실했어요. 분실물은 어떻게 찾나요?', 'CINEMA', default);
insert into ASK values (seq_ask_id.nextval, '1', '주차장문의 입니다', '영화를 보면 주차장은 무료이용인가요?', 'CINEMA', default);
insert into ASK values (seq_ask_id.nextval, '2', '시설 이용 규정', '유모차를 가지고 극장에 입장할 수 있나요?', 'ETC', default);
insert into ASK values (seq_ask_id.nextval, '3', '웹사이트 오류', '웹사이트에서 영화 예매 시 오류가 발생하는데 어떻게 해야 하나요?', 'RESERVATION', default);
insert into ASK values (seq_ask_id.nextval, '4', '장애인 시설', '극장 내 장애인 시설에는 어떤 것들이 있나요?', 'CINEMA', default);
insert into ASK values (seq_ask_id.nextval, '5', '단체 예약', '단체로 영화를 보고 싶은데, 특별한 절차가 필요한가요?', 'RESERVATION', default);


--
-- answer
insert into ANSWER values (seq_answer_id.nextval, 1, 6, '상영시간 이전까지만 가능하며, 상영시간 이후 취소나 환불은 되지 않습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 2, 6, '영화 시간 내 언제든 입장이 가능합니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 3, 6, '상영관 내 외부 음식물 반입은 가능합니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 4, 6, 'CGV는 영화진흥법에 의한 영화별 관람등급을 철저히 준수하고 있습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 5, 6, '분실물의 경우 발견 즉시 현장에서 보관을 진행하고 있으며, 확인 요청시 본인 확인 후 물품 확인을 도와드리고 있습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 6, 6, '영화예매 티켓 하단에 주차장 관련 내용이 적혀있습니다. 영화예매하신분은 주차장요금은 3시간 무료입니다. 참고 부탁드려요..', default);
insert into ANSWER values (seq_answer_id.nextval, 7, 6, '유모차를 가지고 극장에 입장하실 수 있습니다. 그러나 상영관 내에서는 유모차를 접어 보관해야 할 수도 있으니, 입장 전에 직원에게 문의해 주시기 바랍니다. 극장 내에는 유모차를 위한 특별 보관 공간을 마련해 두었습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 8, 6, '웹사이트에서 발생한 오류에 대해 불편을 드려 죄송합니다. 현재 기술 팀이 문제를 해결하기 위해 노력하고 있습니다. 잠시 후 다시 시도해 보시기 바랍니다. 만약 문제가 지속된다면, 고객님의 화면 캡처와 함께 자세한 오류 메시지를 support@example.com 으로 보내주시면 더 빠르게 도움을 드릴 수 있습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 9, 6, '저희 극장은 모든 고객이 편안하게 이용할 수 있도록 다양한 장애인 시설을 갖추고 있습니다. 이에는 휠체어 사용 가능한 좌석, 장애인 화장실, 그리고 우선 예약 서비스가 포함됩니다. 극장 방문 전 또는 방문 시에 직원에게 문의하시면 더 자세한 안내를 받으실 수 있습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 10, 6, '단체 예약을 원하시는 경우, 저희 고객 서비스 센터로 연락 주시면 단체 예약 절차에 대해 안내해 드리겠습니다. 보통 10인 이상부터 단체 예약이 가능하며, 특별 할인이나 좌석 배치에 관한 협의가 필요할 수 있습니다.', default);

--
-- notice
INSERT INTO NOTICE 
VALUES (
  seq_notice_id.nextval, 
  1, 
  'SYSTEM',
  '2024년 3월 시스템점검 안내', 
  '원활하고 안정된 서비스 제공을 위하여 2024년 3월 새벽 시스템 점검 작업이 예정되어 있습니다.' || chr(13)||chr(10) ||
  '점검 시간 중 BOOTBOX홈페이지 및 모바일의 모든 서비스가 중단될 예정이오니 이용에 불편 없으시기 바랍니다.' || chr(13)||chr(10) ||
  chr(13)||chr(10) || -- 빈 줄 추가
  '1. 일시' || chr(13)||chr(10) ||
  chr(13)||chr(10) || -- 빈 줄 추가
  '- 03/29 (화) 02시 ~ 07시 (월요일에서 화요일 넘어가는 새벽)' || chr(13)||chr(10) ||
  chr(13)||chr(10) || -- 빈 줄 추가
  '2. 내용' || chr(13)||chr(10) || 
  chr(13)||chr(10) || -- 빈 줄 추가
  '- 정기 PM작업(시스템 성능 개선 작업), 극장영업 DB 최적화' || chr(13)||chr(10) ||
  '- 작업 대상 : 극장영업시스템, 온라인 서비스(홈페이지/모바일), 광고통합서버(보안 업데이트)' || chr(13)||chr(10) ||
  chr(13)||chr(10) || -- 빈 줄 추가
  '더욱 안정적이고 편리한 서비스를 제공하는 BOOTBOX가 되겠습니다.' || chr(13)||chr(10) ||
  '감사합니다.',
  DEFAULT);

insert into NOTICE values (seq_notice_id.nextval, 2, 'CINEMA', '[센텀시티] 백화점 휴점에 따른 극장 이용 안내',
'안녕하십니까, BOOTBOX 센텀시티입니다.'  || chr(13)||chr(10) ||
chr(13)||chr(10) || -- 빈 줄 추가
'24.03.09(토)~24.03.11(월) 신세계 백화점 센텀시티점 휴점에 따라 극장이용 동선 안내를 공지하오니 이용에 착오 없으시길 바랍니다.'  || chr(13)||chr(10) ||
chr(13)||chr(10) || -- 빈 줄 추가
'■도보/ 대중 교통 이용 시,' || chr(13)||chr(10) ||
 ' : 백화점 3번 GATE (3번 홀 엘리베이터)' || chr(13)||chr(10) ||
 ' : 백화점 4번 GATE (4번 홀 엘리베이터)_ 스파랜드 매장 입구' || chr(13)||chr(10) ||
'■자차 이용 시,' || chr(13)||chr(10) ||
 ' : 지하 3층~4층 엘리베이터 3번 or 4번 홀 이용 가능' || chr(13)||chr(10) ||
 chr(13)||chr(10) || -- 빈 줄 추가
 'BOOTBOX센텀시티 많은이용 부탁드립니다.' || chr(13)||chr(10) ||
'감사합니다.', default);

insert into NOTICE values (seq_notice_id.nextval, 3, 'EVENT', '[웡카] 신규 초콜릿향 진행극장 변경 안내 ',
'안녕하세요. [웡카] 신규 초콜릿향 진행극장이 변경되어 안내드립니다.' || chr(13)||chr(10)||
chr(13)||chr(10) ||
'[웡카] 신규 초콜릿향 진행 극장 (1/31~2/6) BOOTBOX 계양, 광교, 광주터미널, 김해, 대구, 대구스타디움,대구죽전, 대전, 대전터미널, 동수원, 동탄역, 방학, 서면, 센텀시티, 신세계경기, 안산, 연남, 영등포, 왕십리, 용산아이파크몰, 인천,
전주고사, 제주, 청주지웰시티, 판교, 평택' || chr(13)||chr(10) ||
chr(13)||chr(10) ||
'극장 이용 전 확인 부탁드리겠습니다.'  || chr(13)||chr(10) ||
'감사합니다.', default);

insert into NOTICE values (seq_notice_id.nextval, 4, 'ETC', '개인정보처리방침 개정 공지 (24.04.06 시행)',
'2024년 4월 6일 자로 BOOTBOX의 개인정보처리방침이 개정됨에 따라 회원님께 주요 개정 내용과 적용 일정을 아래와 같이 안내 드립니다.'  || chr(13)||chr(10) ||
'1. 개정 약관 시행일'  || chr(13)||chr(10) ||
chr(13)||chr(10) ||
'2024년 1월 23일 (화)'   || chr(13)||chr(10) ||
chr(13)||chr(10) ||
'2. 주요 개정 내용'  || chr(13)||chr(10) ||
chr(13)||chr(10) ||
'- 보다 쉽고 명확한 이해를 위한 약관의 전반적인 문구 정비'  || chr(13)||chr(10) ||
'- 회원의 금지 의무 추가'  || chr(13)||chr(10) ||
'- 게시물의 관리 및 운영 정책 관련 내용 추가'  || chr(13)||chr(10) ||
'- 손해배상 및 면책 관련 문구 수정'  ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'개정 약관에 대한 이의제기 및 문의사항이 있으신 경우, 고객센터(1544-1122)로 문의하여 주시기 바랍니다.' ||chr(13)||chr(10) ||
'감사합니다.', default);

insert into NOTICE values (seq_notice_id.nextval, 5, 'EVENT', '[행사/이벤트]] [BOOTBOX] 무대인사 예매취소 정책 변경 안내',
'안녕하세요, BOOTBOX입니다.'  ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'최근 무대인사 티켓을 대량구매하여 높은 가격으로 재판매하거나 상영 직전 환불하는 등의 사례가 빈번하게 발생하고 있습니다.'  ||chr(13)||chr(10) || 
'이에 CGV는 실제로 행사를 즐기고자 하시는 고객님들께 참여 기회를 제공하기 위하여'  ||chr(13)||chr(10) ||
'무대인사 회차에 대한 예매취소 정책을 아래와 같이 변경하오니 이용에 참고 부탁드립니다.'  ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'기존 : 상영시간 20분 전까지 취소 가능' ||chr(13)||chr(10) ||
'변경 후 : 상영 시간 20분 전까지 매표소에서 취소 가능(키오스크 환불 불가, PRIVATE BOX는 기존과 같이 당일 환불 불가)' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'시행일: 2월 2일 이후 진행하는 무대인사부터 시행' ||chr(13)||chr(10) ||
'감사합니다.', default);

insert into NOTICE values (seq_notice_id.nextval, 6, 'CINEMA', '[BOOTBOX 영등포점] 기계식 주차장 공사 안내',
'안녕하십니까, BOOTBOX 영등포점입니다.' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'먼저 BOOTBOX 영등포점을 방문해주시는 고객님들께 깊은 감사의 말씀드립니다.' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'BOOTBOX 영등포점 입점건물 씨네시티영등포 지하주차장 內 기계식 주차장 공사가 예정되어 일정 안내드립니다.' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
' ● 공사일정 : 2024.03.02(화) ~ 2024.04.30(화)'  ||chr(13)||chr(10) || 
'※ 공사 일정은 상황에 따라 변동 될 수 있습니다.' ||chr(13)||chr(10) || 
chr(13)||chr(10) ||
'공사 기간 중에는 기계식 주차장 이용이 불가하므로 주차 지연 및 입차가 불가할 수 있으니 가급적 대중교통 이용 부탁드립니다.' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'보다 안전하고 편리한 주차공간으로 재 정비하여 찾아 뵙겠습니다. 감사합니다.', default);

insert into NOTICE values (seq_notice_id.nextval, 1, 'ETC', '[BOOTBOX] 이태원 사고에 애도를 표합니다.',
'이태원 사고의 희생자와 유가족분들께'  ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'깊은 애도와 위로의 마음을 전하며,'  ||chr(13)||chr(10) ||
 chr(13)||chr(10) ||
'부상자 분들의 빠른 쾌유를 기원합니다.' ||chr(13)||chr(10) ||
chr(13)||chr(10) ||
'BOOTBOX  일동.', default);

--
-- schedule
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 09:10', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 14:00', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 16:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 09:00', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 11:25', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 13:50', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 2, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 09:45', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 12:30', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 15:15', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 10:30', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 13:15', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 16:00', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 09:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 11:45', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 14:30', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 09:45', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 15:15', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 20:45', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 10:30', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 18:50', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 21:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 17:15', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 20:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 22:45', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 19:10', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 21:20', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 23:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 16, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 09:50', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 16, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 14:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 19:50', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 21:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020102, 16, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 23:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 16, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 09:50', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 16, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-27 14:40', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 17, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 12:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 17, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 16:50', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 17, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-26 12:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 17, To_Date('2024-02-27', 'YYYY-MM-DD'), To_Date('2024-02-26 16:50', 'YYYY-MM-DD HH24:MI'));
--
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020103, 17, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 15:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020104, 15, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 15:00', 'YYYY-MM-DD HH24:MI'));
--Insert Into Schedule Values (Seq_Schedule_Id.Nextval, 020101, 13, To_Date('2024-02-26', 'YYYY-MM-DD'), To_Date('2024-02-26 15:00', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 09:05', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 13:45', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 19:40', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 08:20', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 21:40', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 12:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 1, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 18:20', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 16:05', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 22:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 15:00', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 20:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 12:30', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 23:45', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 10:15', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 14:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 15:10', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 18:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 17:50', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 23:35', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 18:00', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 17, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 21:45', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 18, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 07:00', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 19, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 08:45', 'YYYY-MM-DD HH24:MI'));
----
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 09:05', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 13:45', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 19:40', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 08:20', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 21:40', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 12:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 1, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 18:20', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 16:05', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 22:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 15:00', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 20:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 12:30', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 23:45', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 10:15', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 15, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 14:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 16, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 15:10', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020202, 16, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 18:30', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 16, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 17:50', 'YYYY-MM-DD HH24:MI'));
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 16, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 23:35', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 16, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 18:00', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020204, 17, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 21:45', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020201, 18, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 07:00', 'YYYY-MM-DD HH24:MI'));
--
--insert into SCHEDULE values (seq_schedule_id.nextval, 020203, 19, to_date('2024-02-27', 'YYYY-MM-DD'), to_date('2024-02-27 08:45', 'YYYY-MM-DD HH24:MI'));
----89
--insert into SCHEDULE values (seq_schedule_id.nextval, 020303, 3, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----90
--insert into SCHEDULE values (seq_schedule_id.nextval, 020404, 3, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----91
--insert into SCHEDULE values (seq_schedule_id.nextval, 020503, 4, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----92
--insert into SCHEDULE values (seq_schedule_id.nextval, 020603, 5, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----93
--insert into SCHEDULE values (seq_schedule_id.nextval, 020702, 6, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----94
--insert into SCHEDULE values (seq_schedule_id.nextval, 020801, 7, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----95
--insert into SCHEDULE values (seq_schedule_id.nextval, 020902, 8, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----96
--insert into SCHEDULE values (seq_schedule_id.nextval, 021004, 9, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----97
--insert into SCHEDULE values (seq_schedule_id.nextval, 020401, 10, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----98
--insert into SCHEDULE values (seq_schedule_id.nextval, 020402, 11, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----99
--insert into SCHEDULE values (seq_schedule_id.nextval, 020403, 12, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----100
--insert into SCHEDULE values (seq_schedule_id.nextval, 020501, 13, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----101
--insert into SCHEDULE values (seq_schedule_id.nextval, 020504, 14, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----102
--insert into SCHEDULE values (seq_schedule_id.nextval, 0310503, 15, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----103
--insert into SCHEDULE values (seq_schedule_id.nextval, 0320201, 16, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----104
--insert into SCHEDULE values (seq_schedule_id.nextval, 0320203, 17, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----105
--insert into SCHEDULE values (seq_schedule_id.nextval, 0420304, 18, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----106
--insert into SCHEDULE values (seq_schedule_id.nextval, 0530102, 19, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
----107
--insert into SCHEDULE values (seq_schedule_id.nextval, 0510503, 20, to_date('2024-02-26', 'YYYY-MM-DD'), to_date('2024-02-26 11:40', 'YYYY-MM-DD HH24:MI'));
-- reservation
--INSERT INTO RESERVATION VALUES ('box16443', 1, 1, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box25822', 2, 2, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box47220', 3, 3, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box47211', 2, 3, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box42217', 4, 4, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box34332', 4, 6, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box34331', 5, 6, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box32582', 5, 5, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box35588', 1, 7, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box34209', 1, 89, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box66865', 1, 91, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box83270', 1, 92, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box46618', 1, 93, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box27006', 1, 94, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box83232', 1, 95, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box86379', 1, 96, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box18923', 2, 96, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box31006', 2, 97, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box80338', 2, 98, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box38585', 2, 99, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box77893', 2, 100, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box01976', 2, 101, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box59826', 2, 102, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box72530', 2, 103, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box93725', 3, 104, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box93592', 3, 105, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box27169', 3, 106, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box53041', 3, 107, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box66900', 3, 6, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box25900', 3, 7, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box39403', 3, 90, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box11258', 3, 91, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box20171', 4, 92, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box27355', 4, 93, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box74475', 4, 94, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box12343', 4, 95, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box22202', 4, 96, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box05643', 4, 97, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box98736', 4, 98, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box52027', 4, 99, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box69085', 5, 100, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box38813', 5, 101, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box96400', 5, 102, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box60717', 5, 103, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box61952', 5, 104, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box93500', 5, 105, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box07662', 5, 106, 'CONFIRM');
--INSERT INTO RESERVATION VALUES ('box17935', 5, 107, 'CONFIRM');
--
--
---- 강남점 sample 예약 데이터 추가
--insert into RESERVATION values ('box57869', 1, 10, 'CONFIRM');
--insert into RESERVATION values ('box49687', 1, 11, 'CONFIRM');
--insert into RESERVATION values ('box68574', 1, 12, 'CONFIRM');
--insert into RESERVATION values ('box78960', 2, 13, 'CONFIRM');
--insert into RESERVATION values ('box27586', 2, 14, 'CONFIRM');
--insert into RESERVATION values ('box83657', 2, 15, 'CONFIRM');
-- reservation_seat
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box16443', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box25822', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box47220', 3);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box42217', 4);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box34332', 4);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box32582', 5);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box27006', 5);
--
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box35588', 11);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box34209', 12);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box66865', 13);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box83270', 14);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box46618', 15);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box27006', 16);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box83232', 17);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box86379', 18);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box18923', 22);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box31006', 23);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box80338', 25);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box38585', 27);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box77893', 29);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box01976', 21);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box59826', 20);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box72530', 24);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box93725', 31);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box93592', 32);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box27169', 33);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box53041', 34);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box66900', 35);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box25900', 36);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box39403', 37);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box11258', 38);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box20171', 43);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box27355', 42);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box74475', 41);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box12343', 44);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box22202', 45);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box05643', 46);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box98736', 47);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box52027', 48);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box69085', 51);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box38813', 55);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box96400', 52);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box60717', 53);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box61952', 56);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box93500', 57);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box07662', 58);
--INSERT INTO reservation_seat VALUES (seq_reservation_seat_id.nextval, 'box17935', 59);
--
---- 강남점 좌석 여러개 추가
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box57869', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box57869', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box57869', 3);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box49687', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box49687', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box68574', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box68574', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box68574', 3);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box68574', 4);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box78960', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box27586', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box27586', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box83657', 1);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box83657', 2);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box83657', 3);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box83657', 4);
--insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box83657', 5);
---- order_pay
--insert into ORDER_PAY values ('order1644325835123', 'box16443', 1, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012341234', 'CONFIRM');
--insert into ORDER_PAY values ('order1414325835223', 'box25822', 2, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012345678', 'CONFIRM');
--insert into ORDER_PAY values ('order1544325425262', 'box47220', 3, 'imp32105587', 'html5_inicis', 'card', 12000 , '01011112222', 'CONFIRM');
--insert into ORDER_PAY values ('order2142325845122', 'box34332', 4, 'imp32105587', 'html5_inicis', 'card', 12000 , '01022221111', 'CONFIRM');
--insert into ORDER_PAY values ('order2142311845122', 'box42217', 4, 'imp32105587', 'html5_inicis', 'card', 12000 , '01022221111', 'CONFIRM');
--insert into ORDER_PAY values ('order2244325135126', 'box32582', 5, 'imp32105587', 'html5_inicis', 'card', 12000 , '01013225521', 'CONFIRM');
--insert into ORDER_PAY values ('order2178238136122', 'box27006', 5, 'imp32105587', 'html5_inicis', 'card', 12000 , '01013225521', 'CONFIRM');
----
---- cancel_pay
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 1, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 2, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 3, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 4, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 5, 12000, 'card');
--
-- movie_list
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 03101);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 03101);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 04201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 03201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 04201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 05101);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 03201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 5, 05101);
--
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 15, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 16, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 17, 0201);
---- 강남점 영화 추가
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 5, 0201);
--insert into MOVIE_LIST values (seq_movie_list_id.nextval, 6, 0201);

-- review
--insert into REVIEW values (seq_review_id.nextval, 'box16443', 1, 1, 3, '너무 지루해요', default);
--insert into REVIEW values (seq_review_id.nextval, 'box25822', 2, 2, 4, '최고', default);
--insert into REVIEW values (seq_review_id.nextval, 'box47220', 3, 3, 5, '시간 가는 줄 모르고 봤어용', default);
--insert into REVIEW values (seq_review_id.nextval, 'box47211', 2, 3, 2, '시간 가는 줄 모르고 봤어용', default);
--insert into REVIEW values (seq_review_id.nextval, 'box42217', 4, 4, 5, '짱짱', default);
--insert into REVIEW values (seq_review_id.nextval, 'box34332', 4, 6, 5, '짱짱', default);
--insert into REVIEW values (seq_review_id.nextval, 'box34332', 5, 6, 3, '무난무난', default);
--insert into REVIEW values (seq_review_id.nextval, 'box32582', 5, 5, 2, '재미없어요', default);
--
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box35588', 1, 2, 4, '재미있어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box34209', 1, 3, 3, '괜찮네요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box66865', 1, 4, 5, '최고입니다!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box83270', 1, 5, 2, '별로에요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box46618', 1, 6, 4, '추천합니다', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box27006', 1, 7, 1, '재미없어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box83232', 1, 8, 5, '강추!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box86379', 1, 9, 3, '무난해요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box18923', 2, 9, 3, '볼만해요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box31006', 2, 10, 2, '기대하진 마세요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box80338', 2, 11, 5, '명작입니다!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box38585', 2, 12, 4, '감동적이었어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box77893', 2, 13, 1, '시간 낭비예요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box01976', 2, 14, 4, '재미있게 봤습니다', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box59826', 2, 15, 3, '평범한 편이에요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box72530', 2, 16, 2, '조금 지루했어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box93725', 3, 17, 5, '아주 훌륭해요!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box93592', 3, 18, 3, '보통이에요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box27169', 3, 19, 4, '추천드립니다!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box53041', 3, 20, 1, '별로였어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box66900', 3, 1, 2, '재미없습니다', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box25900', 3, 2, 4, '좋았어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box39403', 3, 10, 3, '괜찮았습니다', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box11258', 3, 4, 5, '대단히 잘 만들었어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box20171', 4, 5, 2, '별로예요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box27355', 4, 17, 5, '완전 재밌어요!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box74475', 4, 7, 3, '시간 가는 줄 몰랐어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box12343', 4, 8, 1, '재미없어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box22202', 4, 9, 4, '추천합니다!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box05643', 4, 10, 2, '기대 이하였습니다', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box98736', 4, 11, 3, '보통이네요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box52027', 4, 12, 4, '좋았어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box69085', 5, 13, 1, '실망스러웠어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box38813', 5, 14, 4, '매우 재미있어요!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box96400', 5, 15, 2, '그저 그래요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box60717', 5, 16, 5, '최고입니다!', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box61952', 5, 17, 3, '괜찮았어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box93500', 5, 18, 4, '재미있게 봤어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box07662', 5, 19, 1, '재미없어요', DEFAULT);
--INSERT INTO REVIEW VALUES (seq_review_id.nextval, 'box17935', 5, 20, 5, '강추합니다!', DEFAULT);
