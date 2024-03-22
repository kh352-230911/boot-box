package com.sh.app;

import com.sh.app.genre.service.GenreService;
import com.sh.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        genreService.fetchAndStoreGenre();
        movieService.scheduledCallApi();
    }
}
