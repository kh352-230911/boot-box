package com.sh.app.cinema.service;

import com.sh.app.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

}
