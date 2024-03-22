# 🎥 BOOT-BOX


## 1. 프로젝트 소개
다양한 장르와 국적의 영화를 통합된 플랫폼에서 즐길 수 있도록 함으로써 사용자들의 편의성을 강화하고
정보 검색의 번거로움을 해소하기 위한 영화 사이트 입니다!
직관적이고 쉽게 이해할 수 있는 인터페이스를 제공하여 사용자가 원하는 영화를 빠르게 찾도록 도와주며
리뷰 작성,평가,추천 기능 등을 통해 사용자들 간의 참여를 활성화 하는데 목표를 두었습니다.

## 2. 주요 기능
1. 최신 영화 정보 제공
    - 메인화면
        1. 메인페이지(인덱스)에 예매율 1~5순위의 영화 정보 제공(예매율도 표시)
        2. 우측상단 영화 검색 기능
        3. 중앙에 영화 트레일러를 배치하여 사용자의 흥미 유도
    - 영화
        1. 영화 전체 목록 조회 (타이틀,예매율,개봉일,관람등급)
        2. 섬네일 선택 시 상세 조회 (타이틀,예매율,개봉일,장르,러닝타임,감독,배우,등급,줄거리,트레일러,관련 리뷰)
2. 극장
    - 극장 메인
      1.지역별-지점순으로 리스트 출력
      2.상단에 자주가는 영화관(즐겨찾기) 출력
      -극장 상세
      1.특정 지점(ex강남점) 선택시 해당 지점 페이지로 이동
      2.지점명,주소,상영관수,전화번호,실시간 길찾기(네이버 지도로 이동)등 상세정보 제공
      3.하단에 현재 상영작 출력 (섬네일)
      4.하단에 금일 기준(기본) 상영시간표 제공.
3. 영화 예매
   -예매
   1.영화 리스트, 지역, 날짜 리스트 출력.
   2.영화,지역,날짜를 선택시 해당하는 상영시간표 출력
   3.하단에 사용자가 무엇을 선택했는지 정보 출력(섬네일,타이틀,극장,일시 등)
   4.비 로그인 회원이라면 다음(좌석 선택) 이용 불가 - 로그인 페이지로 이동
   5.로그인한 회원이면 좌석 선택 페이지로 이동 후 관람인원 / 좌석 선택 후 결제 가능.

3. 정보 관리 페이지
    - 마이 페이지
        1. 내 정보 수정
        2. 나의 문의 내역
        3. 회원탈퇴
        4. 나의 예매 내역
        5. 내가 본 영화
        6. 나의 리뷰 내역
        7. 자주가는 극장
    - 지점 관리자 페이지
        1. 회원 목록
        2. 지점 관리
           -지점 관리자마다 할당받은 지점이 존재
           -상영관 추가, 상영관 삭제
        3. 공지사항 추가
        4. 문의 답변


## 3. Team 소개
- 팀장 : 고혜진
- 팀원 : 유정호, 임초임, 김정효, 정용준, 오승현

[팀원 업무]



- 고혜진
  1.예매 페이지 구현
  1.1영화,극장,날짜 조건에 해당하는 상영일정 출력
  1.2영화관 좌석 배치도와 유사한 UI를 구현
  2.아임포트API를 활용한결제 기능 연동
  1.1 ajax로 사용자가 선택한 내역을 토대로 결제.




- 유정호
  1.인덱스 페이지 예매율 기준 top5 구현 2.영화 검색 기능
  2.장르 별 영화 찾기 기능
  3.영화 별 평균 평점 계산 구현
  4.영화 전체 목록 페이지 구현
  5.spring security를 활용한 로그인/회원가입 구현
  6.구글/카카오 OAuth2 구현
  7.관리자 로그인 구현
  8.회원 정보 수정 / 회원 탈퇴 구현
  9.로그인 한 회원의 마이 페이지 구현
  10.나의 예매 내역/내가 본 영화/나의 문의 내역/리뷰 내역/자주 가는 극장 조회 구현
  11.리뷰 작성 구현

- 임초임
  1.영화 별 상세 페이지 구현
  2.영화 검색과 함께 평점과 유사한 추천 영화  구현
  3.회원/비회원 공지/뉴스 목록 구현
  4.공지/뉴스 상세페이지 구현
  5.회원 문의등록 구현
  6.자주가는 극장 찜하기 구현


- 김정효
  1.관리자 회원 목록 조회 구현
  2.관리자 공지사항 목록, 공지사항 추가 구현
  3.공지사항 자세히보기에서 삭제 구현
  4.관리자 문의사항조회 구현
  5.문의사항 자세히보기 구현


- 정용준
  1.극장 상세 페이지 구현
  1.1.극장 지점 구분 테이블작업 (ex.강남점,왕십리점..)
  1.2 극장 지점 상세 페이지 작업 (지점명,지도,상영 영화,상영스케쥴)
  1.3 극장 상영 스케쥴 작업 (프론트엔드 , 쿼리)
  2.아임포트 API 환경 세팅(API KEY값, 변수설정 등)


- 오승현
  1.지점 관리자 페이지 구현
  1.1 현재 로그인한 어드민이 할당받은 지점이 나오도록 구현
  1.2 본인의 지점에 상영관 추가, 제거





## 4. 기술 스택
<!-- 
정적아이콘 및 뱃지생성 사이트
https://simpleicons.org/?q=intellij
https://shields.io/badges/static-badge
 
simpleicons의 로고명, 컬러를 참조해서 shields.io문법에 따라 뱃지를 만든다.
 
shields.io 기본문법
https://img.shields.io/badge/<텍스트>-<배경색>?logo=<로고>&logoColor=<로그컬러> 
-->

###### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white)
![Javascript](https://img.shields.io/badge/Javascript-F7DF1E?style=flat&logo=JavaScript&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-white?style=flat&logo=jquery&logoColor=0769AD)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-white?style=flat&logo=tailwindcss&logoColor=0769AD)
![Bootstrap](https://img.shields.io/badge/Bootstrap-white?style=flat&logo=bootstrap&logoColor=7952B3)


###### Backend
![Apache Maven](https://img.shields.io/badge/Apache_Maven-8a3578?logo=apachemaven&logoColor=C71A36)

![Spring](https://img.shields.io/badge/Spring-f7f7f7?style=flat&logo=spring&logoColor=6DB33F)
![Spring Boot](https://img.shields.io/badge/Springboot-f7f7f7?style=flat&logo=springboot&logoColor=6DB33F)
![Spring Security](https://img.shields.io/badge/Spring_Security-f7f7f7?style=flat&logo=springsecurity&logoColor=6DB33F)

![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-6DB33F?style=flat&logo=data:image/png;base64,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)
![Oracle](https://img.shields.io/badge/Oracle-4479A1?style=flat&logo=oracle&logoColor=red)
![Redis](https://img.shields.io/badge/Redis-f7f7f7?style=flat&logo=redis&logoColor=DC382D)
![Apache Tomcat](https://img.shields.io/badge/Apache_Tomcat-000000?logo=apachetomcat&logoColor=F8DC75)
![JUnit5](https://img.shields.io/badge/JUnit5-dc524a?logo=junit5&logoColor=25A162)


###### Infra
<!-- 동적 아이콘 생성 사이트 https://techstack-generator.vercel.app/ -->

<div style="display:flex; align-items:flext-start;">
  <img src="https://techstack-generator.vercel.app/github-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://img.shields.io/badge/Github_Actions-f7f7f7?logo=githubactions&logoColor=2088FF"/>
</div>
<div style="display:flex; align-items:flext-start;">
  <img src="https://img.shields.io/badge/Oracle_Cloud-ATP-4479A1?style=squre&logo=oracle&logoColor=red"/>
</div>

<div style="display:flex; align-items:flext-start;">
  <img src="https://techstack-generator.vercel.app/docker-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://techstack-generator.vercel.app/aws-icon.svg" alt="icon" width="20" height="20" />
  <img src="https://img.shields.io/badge/Ubuntu-ffffff?logo=ubuntu&logoColor=E95420"/>
</div>
<div style="display:flex; align-items:flext-start;">
  <img src="https://img.shields.io/badge/Amazon-EC2-f7f7f7?logo=amazonec2&logoColor=FF9900"/>&nbsp;
  <img src="https://img.shields.io/badge/Amazon-S3-f7f7f7?logo=amazons3&logoColor=FF9900"/>&nbsp;
  <img src="https://img.shields.io/badge/Amazon-Route53-f7f7f7?logo=amazonroute53&logoColor=8C4FFF"/>&nbsp;
</div>

###### Tools
![Intellij IDEA](https://img.shields.io/badge/Intellij_IDEA-red?logo=intellijidea)
![VS Code](https://img.shields.io/badge/VS_Code-blue?logo=visualstudiocode)
![Source Tree](https://img.shields.io/badge/Source_Tree-ffffff?style=flat&logo=sourcetree&logoColor=0052CC)
![Discord](https://img.shields.io/badge/discord-5865F2?style=flat&logo=discord&logoColor=white)

240226 v1.0.0

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        h1 {
            color: blue;
        }
        p {
            color: green;
        }
    </style>
</head>
<body>

# 제목

이것은 **Markdown** 파일입니다.

- 리스트 항목 1
- 리스트 항목 2

[링크](https://www.example.com)

</body>
</html>