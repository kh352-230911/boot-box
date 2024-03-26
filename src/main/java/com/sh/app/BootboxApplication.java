package com.sh.app;

import com.sh.app.common.MyUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@EnableScheduling
public class BootboxApplication {

    @Autowired
    private MyUtil myUtil; //autowired는 정적필드에 대해 지원하지 않음.

    public static void main(String[] args) {

        SpringApplication.run(BootboxApplication.class, args);
        System.out.println("========start!========");
    }

    /**
     * @PostConstruct
     * 조건:public,매개변수x,예외처리x
     */
    @PostConstruct
    public void init()
    {
        myUtil.initTest();
    }

}
