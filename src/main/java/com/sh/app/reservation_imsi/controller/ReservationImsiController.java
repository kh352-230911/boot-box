package com.sh.app.reservation_imsi.controller;
import com.sh.app.location.service.LocationService;
import com.sh.app.movie.service.MovieService;
import com.sh.app.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/reservation")

public class ReservationImsiController {

    // 의존 주입 영역
    @Autowired
    private MovieService movieService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    LocationService locationService;


    //첫 예매 페이지 진입 시 날짜(로컬)
    @GetMapping("/reservationMain.do")
    public void reservationMain(Model model)
    {

    }
}
