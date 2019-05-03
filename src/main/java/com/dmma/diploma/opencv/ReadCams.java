package com.dmma.diploma.opencv;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.service.LessonService;
import com.dmma.diploma.service.UnsuccessfulLessonService;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Scope(value = "prototype")
public class ReadCams {
    private VideoCapture capture;
    private FindMoves findMoves = new FindMoves();
    private Lesson lesson;
    private Mat frameMat = new Mat(720, 1280, CvType.CV_8UC3);

    @Autowired
    private LessonService lessonService;
    @Autowired
    private UnsuccessfulLessonService unsuccessfulLessonService;

    public ReadCams() {
    }

    public ReadCams(VideoCapture capture, Lesson lesson) {
        this.capture = capture;
        this.lesson = lesson;

    }


    public void read() {
        if (this.capture.isOpened()) {
            if (capture.read(frameMat)) {
                boolean movesFound = findMoves.isMovesFound(frameMat);
                if (movesFound) {
                    lessonService.setDone(lesson);
                } else {
                    lessonService.setDoneFalse(lesson);
                    LocalDateTime now = LocalDateTime.now();

                    String name = "snapshots/" + now.getDayOfYear() + now.getYear() + now.getHour() + now.getMinute() + now.getSecond() + ".jpg";
                    UnsuccessfulLesson unsuccessfulLesson = new UnsuccessfulLesson(lesson, now, name);
                    unsuccessfulLessonService.saveAndFlush(unsuccessfulLesson);
                    System.out.println(unsuccessfulLesson);
                    Imgcodecs.imwrite(name, frameMat);
                }
            }
        }
    }

    public void setCapture(VideoCapture capture) {
        this.capture = capture;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
