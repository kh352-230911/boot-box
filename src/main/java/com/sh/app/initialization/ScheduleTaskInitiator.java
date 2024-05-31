package com.sh.app.initialization;

import com.sh.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskInitiator {

    @Autowired
    private MovieService movieService;

    @Scheduled(cron = "0 0 3 * * *")
    public void scheduledTask() {
        movieService.scheduledCallApi();
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void calculateReviewRatings() {
        movieService.updateMovieRatings();
    }
}