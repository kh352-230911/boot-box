package com.sh.app.member.service;


import com.sh.app.ask.dto.AskInfoDto;
import com.sh.app.ask.entity.Ask;
import com.sh.app.authority.entity.Authority;
import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.authority.service.AuthorityService;
import com.sh.app.cinema.dto.CinemaInfoDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.location.dto.LocationInfoDto;
import com.sh.app.member.dto.MemberAskDto;
import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.dto.MemberReservationDto;
import com.sh.app.member.dto.MemberReviewDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.repository.MemberRepository;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import com.sh.app.memberLikeGenre.repository.MemberLikeGenreRepository;
import com.sh.app.movie.dto.MovieInfoDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.repository.MovieRepository;
import com.sh.app.reservation.dto.ReservationInfoDto;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.reservation.repository.ReservationRepository;
import com.sh.app.review.dto.ReviewInfoDto;
import com.sh.app.review.dto.ReviewMovieDto;
import com.sh.app.review.entity.Review;
import com.sh.app.review.repository.ReviewRepository;
import com.sh.app.schedule.dto.ScheduleInfomDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.seat.dto.SeatInfoDto;
import com.sh.app.seat.entity.Seat;
import com.sh.app.theater.dto.TheaterInfoDto;
import com.sh.app.theater.entity.Theater;
import com.sh.app.util.GenreNormalization;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional //기존 mvc패턴 처럼 rollback 처리. class 단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
@Slf4j
public class MemberService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberLikeGenreRepository memberLikeGenreRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Member findByMemberLoginId(String username) {
        return memberRepository.findByMemberLoginId(username);
    }

//    public Member createMember(Member member) {
//        memberRepository.save(member);
//        Authority authority = Authority.builder()
//                .memberId(member.getId())
//                .name(RoleAuth.ROLE_USER)
//                .build();
//        authorityService.createAuthority(authority);
//        return member;
//    }
    public Member createMember(Member member) {

        Member savedMember = memberRepository.save(member);

        Authority authority = Authority.builder()
                .memberId(savedMember.getId())
                .name(RoleAuth.ROLE_USER)
                .build();
        authorityService.createAuthority(authority);

        return savedMember;
    }




    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public void logoutAndInvalidateSession() {
        // SecurityContextLogoutHandler 인스턴스 생성
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

        // 로그아웃 처리: 현재 세션 무효화 및 SecurityContext 클리어
        logoutHandler.logout(request, response, null);
    }

    public List<Member> findAll() {
        System.out.println("회원조회 service");
        return memberRepository.findAll();
    }

    public MemberReservationDto findByReservation(Long id) {
        return memberRepository.findByReservation(id).map(member -> {
            Set<ReservationInfoDto> reservationInfoDtos = member.getReservations()
                    .stream().map(reservation -> {
                        // 예약 정보에서 SeatInfoDto로 변환
                        Set<SeatInfoDto> seatInfoDtos = reservation.getSeats()
                                .stream().map(seat -> modelMapper.map(seat, SeatInfoDto.class))
                                .collect(Collectors.toSet());

                        // 예약 정보에서 ScheduleInfomDto로 변환
                        ScheduleInfomDto scheduleInfomDto = modelMapper.map(reservation.getSchedule(), ScheduleInfomDto.class);
                        scheduleInfomDto.setStartTime(reservation.getSchedule().getTime());

                        // 예약 정보의 스케쥴의 상영관과 영화를 각각 dto로 변환
                        TheaterInfoDto theaterInfoDto = modelMapper.map(reservation.getSchedule().getTheater(), TheaterInfoDto.class);
                        MovieInfoDto movieInfoDto = modelMapper.map(reservation.getSchedule().getMovie(), MovieInfoDto.class);

                        scheduleInfomDto.calculateEndTime();

                        // 예약 정보의 스케쥴의 상영관의 극장을 CinemaInfoDto 변환
                        CinemaInfoDto cinemaInfoDto = modelMapper.map(reservation.getSchedule().getTheater().getCinema(), CinemaInfoDto.class);

                        // 예약 정보의 스케쥴의 상영관의 극장의 지점을 LocationInfoDto 변환
                        LocationInfoDto locationInfoDto = modelMapper.map(reservation.getSchedule().getTheater().getCinema().getLocation(), LocationInfoDto.class);

                        cinemaInfoDto.setName(reservation.getSchedule().getTheater().getCinema().getRegion_cinema());
                        cinemaInfoDto.setLocation(locationInfoDto);

                        locationInfoDto.setName(reservation.getSchedule().getTheater().getCinema().getLocation().getLocation_name());

                        theaterInfoDto.setCinema(cinemaInfoDto);

                        scheduleInfomDto.setTheaters(theaterInfoDto);
                        scheduleInfomDto.setMovie(movieInfoDto);

                        ReservationInfoDto reservationInfoDto = modelMapper.map(reservation, ReservationInfoDto.class);
                        reservationInfoDto.setSeats(seatInfoDtos);
                        reservationInfoDto.setSchedule(scheduleInfomDto);

                        return reservationInfoDto;
                    }).collect(Collectors.toSet());

            return MemberReservationDto.builder()
                    .id(member.getId())
                    .name(member.getMemberName())
                    .reservations(reservationInfoDtos)
                    .build();
        }).orElse(null);
    }


    public MemberAskDto findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));

        MemberAskDto memberAskDto = convertToMemberAskDto(member);

        List<Ask> asks = member.getAsks();

        List<AskInfoDto> askInfoDtos = asks.stream()
                .map(this::convertToAskInfoDto)
                .collect(Collectors.toList());

        memberAskDto.setAsks(askInfoDtos);

        return memberAskDto;
    }

    private MemberAskDto convertToMemberAskDto(Member member) {
        return modelMapper.map(member, MemberAskDto.class);
    }

    private AskInfoDto convertToAskInfoDto(Ask ask) {
        return modelMapper.map(ask, AskInfoDto.class);
    }

    public MemberReservationDto findPastReservationsById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));

        List<Reservation> pastReservations = reservationRepository.findPastReservationsById(id);
        
        Set<ReservationInfoDto> reservationInfoDtos = pastReservations.stream()
                .map(this::convertToReservationInfoDto)
                .collect(Collectors.toSet());

        return MemberReservationDto.builder()
                .id(member.getId())
                .name(member.getMemberName())
                .reservations(reservationInfoDtos)
                .build();
    }

    private ReservationInfoDto convertToReservationInfoDto(Reservation reservation) {
        ReservationInfoDto reservationInfoDto = modelMapper.map(reservation, ReservationInfoDto.class);

        ScheduleInfomDto scheduleInfoDto = convertToScheduleInfoDto(reservation.getSchedule());
        Set<SeatInfoDto> seatInfoDtos = convertToSeatInfoDtos(reservation.getSeats());

        Review review = reviewRepository.findByReservationId(reservation.getId()).orElse(null);
        if (review != null) {
            ReviewInfoDto reviewInfoDto = modelMapper.map(review, ReviewInfoDto.class);
            reservationInfoDto.setReview(reviewInfoDto);
        }

        reservationInfoDto.setSchedule(scheduleInfoDto);
        reservationInfoDto.setSeats(seatInfoDtos);
        return reservationInfoDto;
    }

    private Set<SeatInfoDto> convertToSeatInfoDtos(Set<Seat> seats) {
        return seats.stream()
                .map(seat -> modelMapper.map(seat, SeatInfoDto.class))
                .collect(Collectors.toSet());
    }

    private ScheduleInfomDto convertToScheduleInfoDto(Schedule schedule) {
        Movie movie = schedule.getMovie();
        MovieInfoDto movieInfoDto = modelMapper.map(movie, MovieInfoDto.class);
        ScheduleInfomDto scheduleInfomDto = modelMapper.map(schedule, ScheduleInfomDto.class);
        scheduleInfomDto.setStartTime(schedule.getTime());
        scheduleInfomDto.setMovie(movieInfoDto);
        scheduleInfomDto.calculateEndTime();
        TheaterInfoDto theaterInfoDto = convertToTheaterInfoDto(schedule.getTheater());
        scheduleInfomDto.setTheaters(theaterInfoDto);
        return scheduleInfomDto;
    }

    private TheaterInfoDto convertToTheaterInfoDto(Theater theater) {
        TheaterInfoDto theaterInfoDto = modelMapper.map(theater, TheaterInfoDto.class);
        CinemaInfoDto cinemaInfoDto = convertToCinemaInfoDto(theater.getCinema());
        theaterInfoDto.setCinema(cinemaInfoDto);
        return theaterInfoDto;
    }

    private CinemaInfoDto convertToCinemaInfoDto(Cinema cinema) {
        CinemaInfoDto cinemaInfoDto = modelMapper.map(cinema, CinemaInfoDto.class);
        cinemaInfoDto.setName(cinema.getRegion_cinema());
        return cinemaInfoDto;
    }

    public MemberReviewDto getMemberWithReviews(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));

        // Member 엔티티에서 직접 리뷰 목록을 가져옴
        List<Review> reviews = member.getReviews();

        List<ReviewMovieDto> reviewMovieDtos = reviews.stream()
                .map(this::convertToReviewMovieDto)
                .collect(Collectors.toList());

        return MemberReviewDto.builder()
                .reviews(reviewMovieDtos)
                .build();
    }


    private ReviewMovieDto convertToReviewMovieDto(Review review) {
        // 영화 정보를 조회
        Movie movie = movieRepository.findById(review.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + review.getMovieId()));

        // DTO 변환
        MovieInfoDto movieInfoDto = modelMapper.map(movie, MovieInfoDto.class);

        ReviewMovieDto reviewMovieDto = modelMapper.map(review, ReviewMovieDto.class);
        reviewMovieDto.setMovie(movieInfoDto);

        return reviewMovieDto;
    }

    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
    }

    public List<Genre> findMemberLikeGenresByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));
        return member.getMemberLikeGenres().stream()
                .map(MemberLikeGenre::getGenre)
                .collect(Collectors.toList());
    }

    public List<Genre> getMemberPreferredGenres(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));
        return member.getMemberLikeGenres().stream()
                .map(MemberLikeGenre::getGenre)
                .collect(Collectors.toList());
    }

    public void updateMemberGenres(Member member, List<String> preferredGenreNames) {
        // 이전 선호 장르 삭제
        memberLikeGenreRepository.deleteByMember(member);

        // 새로운 선호 장르 추가
        if (preferredGenreNames != null) {
            List<MemberLikeGenre> memberLikeGenres = preferredGenreNames.stream()
                    .map(genreName -> {
                        String GenreName = GenreNormalization.normalizeGenreName(genreName);
                        Genre genre = genreRepository.findByGenreName(GenreName)
                                .orElseThrow(() -> {
                                    log.error("Genre not found: {}", genreName);
                                    return new EntityNotFoundException("Genre not found: " + genreName);
                                });
                        return MemberLikeGenre.builder()
                                .member(member)
                                .genre(genre)
                                .build();
                    })
                    .collect(Collectors.toList());

            memberLikeGenreRepository.saveAll(memberLikeGenres); // 변경사항을 저장
        } else {
            member.setMemberLikeGenres(new ArrayList<>()); // 빈 리스트를 설정합니다.
        }

        memberRepository.save(member);
    }
}
