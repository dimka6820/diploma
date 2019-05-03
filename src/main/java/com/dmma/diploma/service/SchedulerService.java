package com.dmma.diploma.service;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.opencv.ReadCams;
import com.dmma.diploma.repository.LessonRepository;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService implements ApplicationListener<ContextClosedEvent> {
    private static final String CRON = "*/10 * * * * *";
    //"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    private ScheduledExecutorService timer;
    private List<VideoCapture> captureList = new ArrayList<>();

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    LessonService lessonService;

    @Autowired
    private WebApplicationContext context;


    @Scheduled(cron = CRON)
    public void startCameraScan() {
        stopCameraScan();

        List<Lesson> lessons = lessonService.findCurrentLessons();
        timer = Executors.newScheduledThreadPool(lessons.size());

        for (Lesson lesson : lessons) {
            String camera = lesson.getClassRoom().getCamera();
            VideoCapture capture = new VideoCapture(Integer.valueOf(camera));
            if (capture.isOpened()) {
                captureList.add(capture);
                ReadCams readCams = context.getBean(ReadCams.class);
                readCams.setCapture(capture);
                readCams.setLesson(lesson);
                Runnable thread = readCams::read;
                timer.scheduleAtFixedRate(thread, 0, 1, TimeUnit.SECONDS);
            }
        }

    }

    public void stopCameraScan() {
        if (timer != null && !timer.isShutdown()) {
            try {
                timer.shutdown();
                timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

        System.out.println("stopCameraScan: " + captureList);
        for (VideoCapture videoCapture : captureList) {
            if (videoCapture.isOpened()) {
                videoCapture.release();
            }
        }

        captureList.clear();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        stopCameraScan();
    }
}