package com.dmma.diploma.service;

import com.dmma.diploma.opencv.ReadCams;
import org.opencv.videoio.VideoCapture;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {
    private static final String CRON = "*/10 * * * * *";
    private static final String CRON1 = "*/15 * * * * *";
    //"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    private String s = "";
    ScheduledExecutorService timer;
    List<VideoCapture> captureList = new ArrayList<>();
    int i = 1;

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

        if (i == 1) {
            VideoCapture capture = new VideoCapture(0);
            VideoCapture capture1 = new VideoCapture(1);
            captureList.add(capture);
            captureList.add(capture1);

            ReadCams readCams = new ReadCams(capture, "0");
            ReadCams readCams1 = new ReadCams(capture1, "1");

            Runnable thread = () -> {
                readCams.read();
            };
            Runnable thread1 = () -> {
                readCams1.read();
            };
            timer = Executors.newScheduledThreadPool(2);
            timer.scheduleAtFixedRate(thread, 0, 33, TimeUnit.MILLISECONDS);
            timer.scheduleAtFixedRate(thread1, 0, 33, TimeUnit.MILLISECONDS);
        }
        i = 2;
    }

    @Scheduled(cron = CRON1)
    public void sendMailToUsers2() {
        if (timer != null && !timer.isShutdown()) {
            try {
                timer.shutdown();
                timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

        for (VideoCapture videoCapture : captureList) {
            if (videoCapture.isOpened()) {
                videoCapture.release();
            }
        }
    }
}