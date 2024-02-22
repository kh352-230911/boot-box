package com.sh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BootboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootboxApplication.class, args);
        //0207 order +  new date().getTime(); test
        String timeToStr = Long.toString(new Date().getTime());
        System.out.println("order"+timeToStr);
        System.out.println("order"+timeToStr.substring(8));
        //System.out.println("order"+Long.toString(new Date().getTime()).substring(8));//order1707284119924

    }
}
