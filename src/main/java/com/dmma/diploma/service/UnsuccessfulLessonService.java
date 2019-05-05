package com.dmma.diploma.service;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        System.out.println("unsuccessfulLesson1.getImage(): " + unsuccessfulLesson.getImage());
        unsuccessfulLesson.addImage(name);
        System.out.println("unsuccessfulLesson1.getImage(): " + unsuccessfulLesson.getImage());
        unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);


//        UnsuccessfulLesson lastLesson = byLesson.get(0);
//        System.out.println("lastLesson " + lastLesson);
//        for (UnsuccessfulLesson lesson : byLesson) {
//            LocalDateTime lastLessonDateTime = lastLesson.getLocalDateTime();
//            LocalDateTime dateTime = lesson.getLocalDateTime();
//            if (dateTime.isAfter(lastLessonDateTime)) {
//                lastLesson = lesson;
//            }
//        }
//        System.out.println("lastLesson " + lastLesson);
//        int second = lastLesson.getLocalDateTime().getSecond();
//        int newSec = unsuccessfulLesson.getLocalDateTime().getSecond();
//        if (newSec - second > 5) {
//            System.out.println("fghjkl;'");
//            unsuccessfulLessonRepository.saveAndFlush(unsuccessfulLesson);
//        }
    }
}
