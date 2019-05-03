package com.dmma.diploma.service;

import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UnsuccessfulLessonService {
    @Autowired
    private UnsuccessfulLessonRepository unsuccessfulLessonRepository;


    public void saveAndFlush(UnsuccessfulLesson unsuccessfulLesson) {
        System.out.println("UnsuccessfulLessonService");
        List<UnsuccessfulLesson> byLesson = unsuccessfulLessonRepository.findByLesson(unsuccessfulLesson.getLesson());
        System.out.println("byLesson " + byLesson);
        if (CollectionUtils.isEmpty(byLesson)) {
            System.out.println("CollectionUtils.isEmpty(byLesson)) " + CollectionUtils.isEmpty(byLesson));
            unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);
            return;
        }

        UnsuccessfulLesson lastLesson = byLesson.get(0);
        System.out.println("lastLesson " + lastLesson);
        for (UnsuccessfulLesson lesson : byLesson) {
            LocalDateTime lastLessonDateTime = lastLesson.getLocalDateTime();
            LocalDateTime dateTime = lesson.getLocalDateTime();
            if (dateTime.isAfter(lastLessonDateTime)) {
                lastLesson = lesson;
            }
        }
        System.out.println("lastLesson " + lastLesson);
        int second = lastLesson.getLocalDateTime().getSecond();
        int newSec = unsuccessfulLesson.getLocalDateTime().getSecond();
        if (newSec - second > 5) {
            System.out.println("fghjkl;'");
            unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);
        }
    }
}
