package com.sh.app.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyUtil
{
    public void initTest()
    {
        System.out.println("initTest method");
        //saveDBTest();
    }

    @Scheduled(cron = "0 00 14 * * *") // 매일 오후 1시 50분에 실행 혹은 다른 일정으로 설정가능!
    public void saveDBTest()
    {
        System.out.println("이곳에 스케쥴러를 사용하여 특정시간마다 db save를 수행합니다.");
    }
}
