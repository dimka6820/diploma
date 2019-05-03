package com.dmma.diploma.service;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> findCurrentLessons() {
        LocalDateTime now = LocalDateTime.now();

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int weekDay = dayOfWeek.getValue();
        int lessonNumber = TimeLesson.getCurentLessonNumber();

        List<Lesson> lessons = lessonRepository.findCurrentLessons(weekDay, 1, lessonNumber);

        return lessons;
    }

    public void setCancel(Lesson lesson) {
        Boolean canceled = lesson.getCanceled();
        if (canceled == null) {
            lesson.setCanceled(true);
        } else if (canceled) {
            lesson.setCanceled(false);
        } else {
            lesson.setCanceled(true);
        }

        lessonRepository.saveAndFlush(lesson);
    }

    public void setDone(Lesson lesson) {
        Boolean done = lesson.getDone();
        System.out.println("Done: " + done);
        if (done == null || !done) {
            System.out.println("set");
            lesson.setDone(true);
        }

        lessonRepository.saveAndFlush(lesson);
    }

    public void setDoneFalse(Lesson lesson) {
        Boolean done = lesson.getDone();
        System.out.println("Done: " + done);
        if (done == null) {
            System.out.println("set");
            lesson.setDone(false);
        }

        lessonRepository.saveAndFlush(lesson);
    }
}
