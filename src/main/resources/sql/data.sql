-- DML 작성

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
-- seat
insert into SEAT values (seq_seat_id.nextval, 'A01');
insert into SEAT values (seq_seat_id.nextval, 'B01');
insert into SEAT values (seq_seat_id.nextval, 'C01');
insert into SEAT values (seq_seat_id.nextval, 'D01');
insert into SEAT values (seq_seat_id.nextval, 'E01');
--
-- theater
insert into THEATER values (020101, 0201, '1관', 60);
insert into THEATER values (0310101, 03101, '1관', 60);
insert into THEATER values (0320101, 03201, '1관', 60);
insert into THEATER values (0420101, 04201, '1관', 60);
insert into THEATER values (0510101, 05101, '1관', 60);
--
-- genre
insert into GENRE values (seq_genre_id.nextval, '드라마');
insert into GENRE values (seq_genre_id.nextval, '액션');
insert into GENRE values (seq_genre_id.nextval, '코미디');
insert into GENRE values (seq_genre_id.nextval, '판타지');
insert into GENRE values (seq_genre_id.nextval, '범죄');
insert into GENRE values (seq_genre_id.nextval, '멜로');
insert into GENRE values (seq_genre_id.nextval, '느와르');
insert into GENRE values (seq_genre_id.nextval, '스릴러');
insert into GENRE values (seq_genre_id.nextval, '공포');
insert into GENRE values (seq_genre_id.nextval, '전쟁');
insert into GENRE values (seq_genre_id.nextval, '애니메이션');
insert into GENRE values (seq_genre_id.nextval, '로맨스');
insert into GENRE values (seq_genre_id.nextval, '호러');
--
-- movie
insert into
   movie
values (1, '웡카', 'ALL', '2024.01.31', 116,
       'http://h.vod.cgv.co.kr/vodCGVa/87937/87937_222373_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87937/87937_1000.jpg',
       '폴 킹', '티모시 샬라메, 칼라 레인, 올리비아 콜맨, 톰 데이비스', '세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다! 마법사이자 초콜릿 메이커 ‘윌리 웡카’의 꿈은 디저트의 성지, ‘달콤 백화점’에 자신만의 초콜릿 가게를 여는 것.
   가진 것이라고는 낡은 모자 가득한 꿈과 단돈 12소버린 뿐이지만 특별한 마법의 초콜릿으로 사람들을 사로잡을 자신이 있다.
   하지만 먹을 것도, 잠잘 곳도, 의지할 사람도 없는 상황 속에서 낡은 여인숙에 머물게 된 ‘웡카’는
   ‘스크러빗 부인’과 ‘블리처’의 계략에 빠져 눈더미처럼 불어난 숙박비로 인해 순식간에 빚더미에 오른다.
   게다가 밤마다 초콜릿을 훔쳐가는 작은 도둑 ‘움파 룸파’의 등장과 ‘달콤 백화점’을 독점한 초콜릿 카르텔의 강력한 견제까지.
   세계 최고의 초콜릿 메이커가 되는 길은 험난하기만 한데…',
       17.3);
insert into
   movie
values (2, '시민덕희', 'FIFTEEN', '2024.01.24', 114,
       'http://h.vod.cgv.co.kr/vodCGVa/87866/87866_222813_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87866/87866_1000.jpg',
       '박영주', '라미란, 공명, 염혜란, 안은진', '내 돈을 사기 친 그 놈이 구조 요청을 해왔다! 세탁소 화재로 인해 대출상품을 알아보던 생활력 만렙 덕희에게 어느 날, 거래은행의 손대리가 합리적인 대출상품을 제안하겠다며 전화를 걸어온다.
   대출에 필요하다며 이런저런 수수료를 요구한 손대리에게 돈을 보낸 덕희는 이 모든 과정이 보이스피싱이었음을 뒤늦게 인지하고 충격에 빠진다.
   전 재산을 잃고 아이들과 거리로 나앉게 생긴 덕희에게 어느 날 손대리가 다시 전화를 걸어오는데… 이번엔 살려달라는 전화다!
   경찰도 포기한 사건, 덕희는 손대리도 구출하고 잃어버린 돈도 찾겠다는 일념으로
   필살기 하나씩 장착한 직장 동료들과 함께 중국 칭다오로 직접 날아간다.',
       5.9);
insert into
   movie
values (3, '도그데이즈', 'TWELVE', '2024.02.07', 120,
       'http://h.vod.cgv.co.kr/vodCGVa/87978/87978_222744_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87978/87978_1000.jpg',
       '김덕민', '윤여정, 유혜진, 김윤진, 김서형', '깔끔한 성격의 계획형 싱글남 ‘민상’(유해진).영끌까지 모아 산 건물을 개똥밭으로 만드는 세입자 수의사 ‘진영’(김서형) 때문에 매일 머리가 아프다.
    오늘도‘진영’과 티격태격하던 ‘민상’은 동물병원에서 한 성격하는 할머니를 만나는데...',
       8.3);
insert into
   movie
values (4, '데드맨', 'FIFTEEN', '2024.02.07', 108,
       'http://h.vod.cgv.co.kr/vodCGVa/87981/87981_222534_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87981/87981_1000.jpg',
       '하준원', '조진웅, 김희애, 이수경', '목숨값 단돈 500만원! 이름값 1000억? 이름에 살고, 이름에 죽는다! 살아있지만 죽은 사람, 즉 ‘데드맨’이 되어 영문도 모른 채 중국의 사설감옥에 끌려간 ‘이만재’.
   정치 컨설턴트 ‘심여사’가 그의 앞에 나타나 목숨값을 담보로 위험한 제안을 하고,
   ‘이만재’ 때문에 아버지가 죽었다고 주장하는 ‘공희주’가 등장하면서
   1천억짜리 설계판의 배후를 찾기 위해 의기투합한 세 사람의 추적이 시작되는데…',
       4.0);
insert into
   movie
values (5, '아가일', 'TWELVE', '2024.02.07', 139,
       'http://h.vod.cgv.co.kr/vodCGVa/87426/87426_220043_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87426/87426_1000.jpg',
       '매튜 본', '헨리 카빌, 브라이스 달라스 하워드, 샘 록웰, 두아 리파', '내가 쓴 베스트셀러 스파이 소설이 현실이 되었습니다?! 현실감 넘치는 스파이 세계를 구현한 책 ‘아가일’로 엄청난 성공을 거둔 베스트셀러 작가 ‘엘리’.
   소설의 마지막 권을 앞둔 그녀는 자기도 모르는 사이 수많은 적들에게 둘러 쌓이고 그녀 앞에 갑자기 추레한 행색의 현실 스파이 ‘에이든’이 나타나 그녀를 구해준다.
   그는 그녀의 소설 ‘아가일’ 속 사건이 현실이 되었고 그로 인해 엘리가 전세계 스파이들이 표적이 되었다고 말한다.
   자신을 쫓는 전세계의 스파이들로부터 벗어나기 위해, 엘리는 소설의 다음 챕터를 쓰고 그 안의 단서를 바탕으로 현실의 레전드 요원 아가일을 찾아야만 한다!',
       3.0);
insert into
   movie
values (6, '소풍', 'TWELVE', '2024.02.07', 114,
       'http://h.vod.cgv.co.kr/vodCGVa/87999/87999_222674_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87999/87999_1000.jpg',
       '김용균', '나문희, 김영옥, 박근형, 류승수',
       '60년 만에 찾아간 고향, 16살의 추억을 만났다. 요즘 들어 돌아가신 엄마가 자꾸 꿈에 보이는 은심(나문희). 마침 절친이자 사돈 지간인 금순(김영옥)이 연락도 없이 불쑥 찾아오자, 은심은 금순과 함께 고향 남해로 떠나기로 한다. 그곳에서 우연히 자신을 짝사랑하던 태호(박근형)를 만나며 잊고 지낸 추억을 하나둘씩 떠올리게 되는데…“다음에 다시 태어나도 네 친구 할 끼야” 한 편의 시가 되는 우정, 어쩌면 마지막 소풍이 시작된다.',
       3.5);
insert into
   movie
values (7, '아기상어 극장판-사이렌 스톤의 비밀', 'ALL', '2024.02.07', 84,
       'http://h.vod.cgv.co.kr/vodCGVa/87993/87993_222614_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87993/87993_1000.jpg',
       '알란 포맨', '장예나, 전태열, 쓰복만, 씨엘', '대도시 미끈매끈 시티로 이사한 아기상어 ‘올리’! 최고의 단짝 ‘윌리엄’과 헤어져야 한다는 슬픔도 잠시, 뉴욕처럼 반짝이는 화려한 풍경과 멋진 음악에 설렌다. 벨루가 아이돌 ‘엔하이픈’, 상어 팝스타 ‘샤키L’, 그리고 최고의 스타 불가사리 ‘스타리아나’까지! 어느 날 ‘올리’는 ‘스타리아나’의 인기 비결,
   사이렌 스톤’의 숨겨진 비밀과 스타리아나’의 거대한 음모를 알게 되는데… 아기상어, 스톤의 저주에 맞서 바다를 지켜라!',
       2.4);
insert into
   movie
values (8, '정글번치-월드투어', 'ALL', '2024.01.31', 89,
       'http://h.vod.cgv.co.kr/vodCGVa/87972/87972_222612_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87972/87972_1000.jpg',
       '로랑 브루', '석승훈, 정해은, 장병관, 박시윤', '정글에 부글부글 거품 폭탄이?! 두더지 악당의 습격으로 폭발 위기에 처한 정글! 정글을 구해야만 해! 정글을 구하려면
     전설의 ‘알버트’ 박사를 찾아서 해독제를 만들어야 해! 눈보라 산, 사막, 비밀 동굴,
     대나무 숲까지 전 세계로 떠난 정글번치! 과연 두더지 악당의 추격을 피해 박사를 찾고 정글을 구할 수 있을까? 올 겨울방학, 정글 구하는 김에 세계일주 가자고!!',
       1.6);
insert into
   movie
values (9, '추락의 해부', 'FIFTEEN', '2024.01.31', 152,
       'http://h.vod.cgv.co.kr/vodCGVa/87979/87979_222625_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87979/87979_1000.jpg',
       '쥐스틴 트리에', '산드라 휠러, 스완 아를로, 밀로 마차도 그라너', '남편의 추락사로 한순간에 유력한 용의자로 지목된 유명 작가 ‘산드라’. 유일한 목격자는 시각장애가 있는 아들과 안내견뿐. 단순한 사고였을까? 아니면 우발적 자살 혹은 의도된 살인? 사건의 전말을 해부해 가는 제76회 칸영화제 황금종려상 수상작',
       1.0);
insert into
   movie
values (10, '서울의 봄', 'TWELVE', '2023.11.22', 141,
       'http://h.vod.cgv.co.kr/vodCGVa/87554/87554_220955_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87554/87554_1000.jpg',
       '김성수', '황정민, 정우성, 이성민, 박해준', '1979년 12월 12일, 수도 서울 군사반란 발생 그날, 대한민국의 운명이 바뀌었다. 대한민국을 뒤흔든 10월 26일 이후,
     서울에 새로운 바람이 불어온 것도 잠시 12월 12일, 보안사령관 전두광이 반란을 일으키고 군 내 사조직을 총동원하여 최전선의 전방부대까지 서울로 불러들인다. 권력에 눈이 먼 전두광의 반란군과 이에 맞선 수도경비사령관 이태신을 비롯한 진압군 사이, 일촉즉발의 9시간이 흘러가는데… 목숨을 건 두 세력의 팽팽한 대립 오늘 밤, 대한민국 수도에서 가장 치열한 전쟁이 펼쳐진다!',
       0.4);
insert into
   movie
values (11, '아네모네 ', 'FIFTEEN', '2024.02.27', 75,
       'http://h.vod.cgv.co.kr/vodCGVa/87998/87998_222685_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87998/87998_1000.jpg',
       '정하용', '정이랑, 박성진', '오늘 하루도 열심히 밥벌이한 집안의 가장 ‘용자’가 오늘 하루도 역시나 밥만 축낸 백수 남편 ‘성진’에게 오늘 하루만 오로지 부탁한 심부름이 있다. 그리하여 오늘 용자가 성진에게 묻고 싶은 단 한 마디 “로또 샀어, 안 샀어?” 1등 당첨 로또를 쟁취하기 위한 용자들의 필사의 레이스가 시작된다!',
       0.4);
insert into
   movie
values (12, '신차원! 짱구는 못말려 더 무비 초능력 대결전 ~날아라 수제김밥~', 'ALL', '2023.12.22', 94,
       'http://h.vod.cgv.co.kr/vodCGVa/87888/87888_221575_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000087/87888/87888_1000.jpg',
       '요네 히토시', '박영남, 강희선, 김환진', '최초의 3D CG! 제작 기간 7년 최고의 웃음과 감동! 최강의 스케일 옷까지 갈아입은 볼록 짱구 등장! 어느 날, 우주에서 날아온 검은 빛과 하얀 빛이 떡잎마을을 향해 떨어진다. 평소처럼 저녁밥을 손꼽아 기다리던 짱구는 하얀 빛에 정통으로 맞게 되고 그러자 온몸에 넘치는 신비한 힘! 힘에 몸을 맡긴 채 엉덩이에 의식을 집중하자 장난감들이 붕붕 떠오른다.
"엉덩이... 엉덩이가 뜨거워...!? 뭔지 몰라도 엄청난 힘을 손에 넣은 것 같아." 한편, 검은 빛을 통해 초능력을 손에 넣은 또 다른 남자는 이 세상의 파멸을 바라며 폭주하기 시작하는데, 위기에 처한 세상을 구하기 위한 유일한 희망이 바로 짱구...!? 올 겨울, 짱구의 엉덩이가 뜨겁게 타오른다!',
       0.1);
insert into
   movie
values (13, '킹덤-엑소더스', 'EIGHTEEN', '2024.01.31', 317,
       'http://h.vod.cgv.co.kr/vodCGVa/88016/88016_222917_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000088/88016/88016_1000.jpg',
       '라스 폰 트리에', '보딜 요르겐센, 미카엘 페르스브란트, 라스 미켈센, 니콜라스 브로', '“선도 악도 있음을 명심하라” 코펜하겐 최고의 종합병원 ‘킹덤’. 음산하고 기묘한 기운이 감도는 어느 날 밤, 몽유병자 카렌이 알 수 없는 힘에 이끌려 ‘킹덤’ 앞에 도착한다. 사악한 악으로부터 ‘킹덤’을 구하기 위해 25년간 풀지 못한 비밀을 찾기 시작하는데… ‘킹덤’의 입구가 다시 열리고 숨어있던 절대 악이 부활한다!',
       0.1);
insert into
   movie
values (14, '상견니 ', 'FIFTEEN', '2024.01.25', 107,
       'http://h.vod.cgv.co.kr/vodCGVa/86750/86750_211203_1200_128_960_540.mp4', 'https://img.cgv.co.kr/Movie/Thumbnail/Poster/000086/86750/86750_1000.jpg',
       '황천인', '가가연, 허광한, 시백우', '드라마 스토리를 영화 버전으로 확장시킨 멀티버스 판타지 로맨스 완전히 새로운 세계관, 완전히 새로운 스토리의 <상견니> 2009년, 리쯔웨이와 황위쉬안은 밀크티 가게에서 우연히 재회한다. 처음 만났지만 마치 오래전부터 알고 있었던 것 같은 기시감과 묘한 설렘을 느끼는 두 사람. 이들은 사계절을 함께 보내며 가까워지고, 2010년의 마지막 날, 함께 새해를 맞이하며 연인이 된다. 2017년, 황위쉬안의 인생에 예상치 못한 변화가 생긴다. 해외 발령을 받게 된 것. 황위쉬안은 이 제안을 받아들이고 새로운 여정을 시작하지만 이 선택은 그녀의 미래를 바꿀 뿐만 아니라, 리쯔웨이와 모쥔제, 그리고 그녀가 아직 모르는 천윈루의 운명까지 뒤바꿔 놓는데… 이제, 이들은 수없이 뒤엉킨 타임라인 속에서 서로를 구하기 위해 낡은 테이프 속 들려오는 노래 ‘라스트 댄스’를 따라 달려가기 시작한다.',
       0.2);
--
-- movie_genre
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 4, 1);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 1);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 2);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 3);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 5, 4);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 2, 5);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 5);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 6);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 7);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 8);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 9);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 9);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 3, 11);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 11);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 2, 11);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 11, 12);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 13, 13);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 8, 13);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 1, 14);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 4, 14);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 12, 14);
insert into MOVIE_GENRE values (seq_movie_genre_id.nextval, 6, 14);
--
-- member_like_genre
insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 1, 1);
insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 2, 1);
insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 3, 4);
insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 4, 3);
insert into MEMBER_LIKE_GENRE values (seq_member_like_genre_id.nextval, 5, 2);
--
-- member_like_cinema
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 1, 0201, 03101, 03201);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 2, 0201, null, null);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 3, 03101, 05101, null);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 4, 04201, null, null);
insert into MEMBER_LIKE_CINEMA values (seq_member_like_cinema_id.nextval, 5, 03101, 03201, 05101);
--
-- ask
insert into ASK values (seq_ask_id.nextval, 1, '예매 취소 및 환불', '예매 취소 및 환불 규정은 어떻게 되나요?', default);
insert into ASK values (seq_ask_id.nextval, 2, '영화 시간보다 늦었어요.', ' 영화 시간보다 늦었어요. 입장 가능한가요?', default);
insert into ASK values (seq_ask_id.nextval, 3, '음식물 반입', '상영관 내 다른 음식물의 반입이 되나요?', default);
insert into ASK values (seq_ask_id.nextval, 4, '관람 등급', '관람 등급에 대해 알고 싶습니다.', default);
insert into ASK values (seq_ask_id.nextval, 5, '영화 관람을 하다 소지품을 분실했어요', '영화 관람을 하다 소지품을 분실했어요. 분실물은 어떻게 찾나요?', default);
--
-- answer
insert into ANSWER values (seq_answer_id.nextval, 1, 6, '상영시간 이전까지만 가능하며, 상영시간 이후 취소나 환불은 되지 않습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 2, 6, '영화 시간 내 언제든 입장이 가능합니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 3, 6, '상영관 내 외부 음식물 반입은 가능합니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 4, 6, 'CGV는 영화진흥법에 의한 영화별 관람등급을 철저히 준수하고 있습니다.', default);
insert into ANSWER values (seq_answer_id.nextval, 5, 6, '분실물의 경우 발견 즉시 현장에서 보관을 진행하고 있으며, 확인 요청시 본인 확인 후 물품 확인을 도와드리고 있습니다.', default);
--
-- notice
insert into NOTICE values (seq_notice_id.nextval, 1, 'SYSTEM', '2023년 12월 시스템점검 안내', '원활하고 안정된 서비스 제공을 위하여 2023년 12월 새벽 시스템 점검 작업이 예정되어 있습니다.');
insert into NOTICE values (seq_notice_id.nextval, 2, 'CINEMA', '[센텀시티] 백화점 휴점에 따른 극장 이용 안내', '22.09.09(금)~22.09.10(토) 신세계 백화점 센텀시티점 휴점에 따라 극장이용 동선 안내를 공지하오니 이용에 착오 없으시길 바랍니다.');
insert into NOTICE values (seq_notice_id.nextval, 3, 'EVENT', '신규 초콜릿향 진행극장 변경 안내 ', '4DX 신규 초콜릿향 진행 극장 (1/31~2/6) CGV 계양, 광교, 광주터미널, 김해, 대구, 대구스타디움');
insert into NOTICE values (seq_notice_id.nextval, 4, 'ETC', '개인정보처리방침 개정 공지 (23.11.06 시행)', '2023년 11월 6일 자로 BOOTBOX의 개인정보처리방침이 개정됨에 따라 회원님께 주요 개정 내용과 적용 일정을 아래와 같이 안내 드립니다.');
insert into NOTICE values (seq_notice_id.nextval, 5, 'ETC', 'BOOTBOX 서비스 이용약관 개정 안내 (2024년 1월 23일 시행)', '2024년 1월 23일자로 BOOTBOX 서비스 이용약관을 일부 개정하게 되어 아래와 같이 사전 안내드립니다. 서비스 이용에 참고 부탁드립니다.');
--
-- schedule
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 1, '2024-02-01', '15:00');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 2, '2024-02-01', '20:00');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 3, '2024-02-03', '10:00');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 4, '2024-02-04', '22:00');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 5, '2024-02-06', '19:00');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 6, '2024-01-24', '12:30');
insert into SCHEDULE values (seq_schedule_id.nextval, 020101, 6, '2024-01-29', '20:30');
--
-- reservation
insert into RESERVATION values ('box16443', 1, 1, 'CONFIRM');
insert into RESERVATION values ('box25822', 2, 2, 'CONFIRM');
insert into RESERVATION values ('box47220', 3, 3, 'CONFIRM');
insert into RESERVATION values ('box42217', 4, 4, 'CONFIRM');
insert into RESERVATION values ('box34332', 4, 6, 'CONFIRM');
insert into RESERVATION values ('box32582', 5, 5, 'CONFIRM');
insert into RESERVATION values ('box21482', 5, 7, 'CONFIRM');
insert into RESERVATION values ('box47211', 2, 2, 'CONFIRM');
--
-- reservation_seat
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box16443', 1);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box25822', 2);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box47220', 3);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box42217', 4);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box34332', 4);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box32582', 5);
insert into reservation_seat values (seq_reservation_seat_id.nextval, 'box21482', 5);
--
-- order_pay
insert into ORDER_PAY values ('order1644325835123', 'box16443', 1, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012341234', 'CONFIRM');
insert into ORDER_PAY values ('order1414325835223', 'box25822', 2, 'imp32105587', 'html5_inicis', 'card', 12000 , '01012345678', 'CONFIRM');
insert into ORDER_PAY values ('order1544325425262', 'box47220', 3, 'imp32105587', 'html5_inicis', 'card', 12000 , '01011112222', 'CONFIRM');
insert into ORDER_PAY values ('order2142325845122', 'box34332', 4, 'imp32105587', 'html5_inicis', 'card', 12000 , '01022221111', 'CONFIRM');
insert into ORDER_PAY values ('order2142311845122', 'box42217', 4, 'imp32105587', 'html5_inicis', 'card', 12000 , '01022221111', 'CONFIRM');
insert into ORDER_PAY values ('order2244325135126', 'box32582', 5, 'imp32105587', 'html5_inicis', 'card', 12000 , '01013225521', 'CONFIRM');
insert into ORDER_PAY values ('order2178238136122', 'box21482', 5, 'imp32105587', 'html5_inicis', 'card', 12000 , '01013225521', 'CONFIRM');
--
-- cancel_pay
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 1, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 2, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 3, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 4, 12000, 'card');
insert into CANCEL_PAY values (seq_cancel_pay_id.nextval, 5, 12000, 'card');
--
-- movie_list
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 0201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 1, 03101);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 03101);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 2, 04201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 03201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 04201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 3, 05101);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 03201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 4, 0201);
insert into MOVIE_LIST values (seq_movie_list_id.nextval, 5, 05101);
--
-- review
insert into REVIEW values (seq_review_id.nextval, 'box16443', 1, 1, 3, '너무 지루해요', default);
insert into REVIEW values (seq_review_id.nextval, 'box25822', 2, 2, 4, '최고', default);
insert into REVIEW values (seq_review_id.nextval, 'box47220', 3, 3, 5, '시간 가는 줄 모르고 봤어용', default);
insert into REVIEW values (seq_review_id.nextval, 'box47211', 2, 3, 2, '시간 가는 줄 모르고 봤어용', default);
insert into REVIEW values (seq_review_id.nextval, 'box42217', 4, 4, 5, '짱짱', default);
insert into REVIEW values (seq_review_id.nextval, 'box34332', 4, 6, 5, '짱짱', default);
insert into REVIEW values (seq_review_id.nextval, 'box34332', 5, 6, 3, '무난무난', default);
insert into REVIEW values (seq_review_id.nextval, 'box32582', 5, 5, 2, '재미없어요', default);