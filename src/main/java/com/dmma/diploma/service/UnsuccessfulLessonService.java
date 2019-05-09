package com.dmma.diploma.service;

import com.dmma.diploma.common.Constants;
import com.dmma.diploma.model.Lesson;
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


    public void saveAndFlush(Lesson lesson, String name) {
        System.out.println("UnsuccessfulLessonService");

        UnsuccessfulLesson unsuccessfulLesson = unsuccessfulLessonRepository.findByLesson(lesson);
        System.out.println("byLesson1 ");
        if (unsuccessfulLesson == null) {
            UnsuccessfulLesson newUnsuccessfulLesson = new UnsuccessfulLesson(lesson, name);
            System.out.println("CollectionUtils.isEmpty(byLesson)) ");
            unsuccessfulLessonRepository.saveAndFlush(newUnsuccessfulLesson);
            return;
        }

        List<String> images = unsuccessfulLesson.getImage();
        if (CollectionUtils.isEmpty(images)) {
            unsuccessfulLesson.addImage(name);
            unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);
            return;
        }

        LocalDateTime currentDateTime = LocalDateTime.parse(name, Constants.DATE_TIME_FORMATTER);
        LocalDateTime lastDateTime = LocalDateTime.parse(images.get(0), Constants.DATE_TIME_FORMATTER);
        System.out.println("currentDateTime " + currentDateTime);
        System.out.println("lastDateTime " + lastDateTime);
        for (String image : images) {
            LocalDateTime dateTime = LocalDateTime.parse(image, Constants.DATE_TIME_FORMATTER);
            if (dateTime.isAfter(lastDateTime)) {
                lastDateTime = dateTime;
            }
        }

        System.out.println("lastDateTime " + lastDateTime);
        LocalDateTime newMinusFive = currentDateTime.minusSeconds(5L);
        if (newMinusFive.isAfter(lastDateTime)) {
            unsuccessfulLesson.addImage(name);
            System.out.println("unsuccessfulLesson1.getImage(): ");
            unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);
        }

    }
}
