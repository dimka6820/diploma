package com.dmma.diploma.opencv;

import com.dmma.diploma.common.Constants;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.service.LessonService;
import com.dmma.diploma.service.UnsuccessfulLessonService;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
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

                    String name = now.format(Constants.DATE_TIME_FORMATTER);
                    System.out.println(name);

                    unsuccessfulLessonService.saveAndFlush(lesson, name, frameMat);
//                    Imgcodecs.imwrite(Constants.FOLDER + name + Constants.POSTFIX, frameMat);
                    frameMat.release();
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
