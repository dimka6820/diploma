package com.dmma.diploma.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SchedulerService {
    private static final String CRON = "*/10 * * * * *";
    //"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    private String s = "";
    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int value = dayOfWeek.getValue();

        int minute = now.getMinute();
        int second = now.getSecond();
        System.out.println(s);
        System.out.println(minute + ":" + second);
        s = minute + ":" + second;
    }
}
