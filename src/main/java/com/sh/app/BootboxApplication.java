package com.sh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BootboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootboxApplication.class, args);
        //0207 order +  new date().getTime(); test
        System.out.println("order"+new Date().getTime());//order1707284119924


    }
}
