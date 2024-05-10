# 🎥 BOOT-BOX 
### By.CODE DIRECTOR


## 1. 프로젝트 소개
직관적이고 쉽게 이해할 수 있는 인터페이스를 제공하여 사용자가 원하는 영화를 빠르게 찾도록 도와주며
리뷰 작성,평가,추천 기능 등을 통해 사용자들 간의 참여를 활성화 하는데 목표를 둔 영화 사이트 입니다.

## 2. 주요 기능
- 메인
    - 인기 영화 무비 트레일러 자동 재생
    - 영화 검색 , 무비 차트 및 선호 장르 추천 영화 정보 제공
- 영화
    - API를 활용한 포스터,개봉일,줄거리,배우진 등의 정보 제공
    - 사이트 내 예매율과 회원들의 평점 조회 가능
- 극장
    - CGV를 벤치마킹하여 실존하는 영화관 정보 제공
    - 상영스케쥴 확인 및 선호극장(찜) 가능
- 예매
    - 영화,지점,날짜를 선택하여 상영 스케쥴을 출력
    - 직관적인 ui로 사용자가 쉽게 좌석을 선택할 수 있도록 기능 구현
    - 아임포트 API를 통해 결제 및 환불 기능 제공
- 회원 
    - 회원 가입 및 정보 수정
    - 예매 내역, 예매 취소, 관람 영화 조회, 리뷰 등록, 문의사항 등록
- 관리자
    - 문의사항 답변, 공지사항 등록
    - 슈퍼관리자 : 상영스케쥴 신청 수락 
    - 지점관리자 : 상영스케쥴 신청 , 지점 상영관/영화 CRUD
  
## 3. Team 소개 
- 고혜진(팀장)
  - 예매 기능,결제 구현
  - 지점관리자 기능 구현
- 유정호(부팀장)
  - 영화 기능 구현 (API,스케쥴링-예매율 UPDATE)
  - 회원 CRUD 외 예매 정보 구현
- 임초임
  - 영화 기능 구현 (UI외 다수)
  - 관리자 기능 구현
- 김정효
  - 관리자 기능 구현
- 정용준
  - 극장 정보 구현 (위치, 정보, 상영 스케쥴)
- 오승현
  - 지점관리자 페이지 구현(할당 지점,상영관CRUD)

    
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

![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-6DB33F?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQCAMAAAC3Ycb+AAAAFVBMVEWHwWNssz7o8+HQ58L///+32qKj0IisqGZmAAAACXBIWXMAAAsTAAALEwEAmpwYAAAGaUlEQVR4nO3d3VbaWgBG0RSOvP8jnxBQUYLiXzKBtXrVUdpGpjuhvfmG/4pqWPsC6m2BYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCFQhWIFiBYAWCdVsgm7HtZjv2dGx39uPY/kXb/es3a1/017JBxrd/fOt3u90wDP9+0vj7xz9lcsKFRJC9wt7gRwSf+Iw4Jo0Estmfhj9kuCBjwRggI8XCEu9h9i5rvwtTa4OsTnHayLK6yoogm61DcdLKh2UdkPFciBavrXdWlgeR7lEfN+xWOCrLgugH47zFj8piIOgT46qWRFkKZLf2m/rTnhZ6owK5skCwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrECwAsEKBCsQrEDmGobDqO3JCPHzDvH2dLL4aRrQPWzo/tKEayD/jpPC0xv+09Hn/cr0Yef4u0APDHJYDv7TxbrNYXb3KzQPCLLKUPBmGqYO5B3FFWunh5vO9uT5cHhAzHZ8wcsT5tM73ueTvA8C8hHF87r9bz2WXx9Jl3g+Yrl/kL3FzAu/cXv/TpPN/INqfqj3rkFmLVaYt5+uZf6Qng2T3i3IcP7lC5vqw9wS8ebkuu4RZGboF1vInTktz1d4byBPZ9+Am/WPxYXOD/F4rfcG8ibsXMx14SPH37c4yC1Nqq+xcL8kyA0cjPOWXrhfCuQmMV4alrt/Ef91cgvd20M9kCsL5MoCwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoEKxCsQLACwQoE695Abmg1ZLZhqXmE5fZDbnewYtGxnYUXdm5pX2ff0nMuq2xQASN5V7XG4NGCCzvvfmoflfODsdho8nKfss6/4cinysw823ik7+9T1vFj79y0pHIHG2ZHSJ8v7k5Bpi98buR0VZb5Ody3l3THIMf3YG4ReHJZDmYaJp57cs+skN47yOENufQ5Zr+kvvsrmuOq+vxj+uJRfQiQo8rljfv/nmfuR5790P13hIZp134kGA/DxWn7fdsPP2M8Dsgry7Uf+zdT21FqvvGXDl15bVc9wB4N5OXb+QswP2z/xLr+o8SDgpzKTDS//Q+ywx3wG8+nRwd5i/P6ENhur74ZHW5s2x8+ggL5WsP7/ujvCUQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgQrEKxAsALBCgTr3kA2yDbeN1tugXXJ6VV73fNSCy+wLr2Fu3m6pZvXCnO4K4wTm5Or71t+J/rQGiBTM9uNTB8P+f1tq4FMcSqfjCou0LogU8Y88bDOfPpZAMihhWdwTylWPxWnMSDHphXcRWD2G5aSxDEN5KXDDuful4fwjsPEnsNLLMibTgciDxORH04WPr9gevXT8yyxi3DabYA8UIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYIVCFYgWIFgBYL1P4BOyvTPmRoKAAAAAElFTkSuQmCC)
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
            color: darkred;
        }
        h3 {
            color: gray;
        }
        p {
            color: green;
        }
        hr {
            color: brown;
        }
    </style>
</head>
<body>

