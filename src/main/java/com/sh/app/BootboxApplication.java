package com.sh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BootboxApplication {
    public static void main(String[] args) {

        //order test용임 신경안쓰셔도됩니다.
        SpringApplication.run(BootboxApplication.class, args);
        //0207 order +  new date().getTime(); test
        System.out.println("order"+new Date().getTime());//order1707284119924
    }

}
