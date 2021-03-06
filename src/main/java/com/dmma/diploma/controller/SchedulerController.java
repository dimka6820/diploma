package com.dmma.diploma.controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.ClassRoomRepository;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.dmma.diploma.service.LessonService;
import com.dmma.diploma.service.TimeLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SchedulerController {

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    UnsuccessfulLessonRepository unsuccessfulLessonRepository;
    @Autowired
    LessonService lessonService;

    @RequestMapping(value = "/classroom", method = RequestMethod.GET)
    public String showClassRoom(ModelMap model) {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        Map<Integer, List<ClassRoom>> listMap = new HashMap<>();
        for (ClassRoom classRoom : classRooms) {
            Integer body = classRoom.getBody();
            List<ClassRoom> byBody = classRoomRepository.findByBody(body);
            listMap.put(body, byBody);
        }

        model.put("classRoomByBody", listMap);
        model.put("classrooms", classRooms);

        List<Lesson> lessons = lessonService.findCurrentLessons();

        model.put("currentLessons", lessons);
        model.put("currentLessonNumber", TimeLesson.getCurentLessonNumber());

        return "scheduler/classroom";
    }

    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String showLesson(ModelMap model, @RequestParam Long lessonId) {
        Lesson lesson = lessonRepository.findOne(lessonId);
        model.put("lesson", lesson);
        UnsuccessfulLesson unsuccessfulLesson = unsuccessfulLessonRepository.findByLesson(lesson);
        model.put("unsuccessfulLesson", unsuccessfulLesson);
        return "lesson/lesson";
    }

    @RequestMapping(value = "/scheduler", method = RequestMethod.GET)
    public String showScheduler(ModelMap model, @RequestParam Integer body, @RequestParam String number) {

        ClassRoom classRoom = classRoomRepository.findByNumberAndBody(number, body);

        model.put("lesson1", getLessonMatrix(1, classRoom));
        model.put("lesson2", getLessonMatrix(2, classRoom));
        model.put("classRoom", classRoom.getId());

        return "scheduler/scheduler";
    }

    private Lesson[][] getLessonMatrix(int weekNumber, ClassRoom classRoom) {
        Lesson[][] lessons = new Lesson[8][6];
        List<Lesson> lessonWeekNumber = lessonRepository.findByWeekNumberAndClassRoom(weekNumber, classRoom);
        for (Lesson lesson : lessonWeekNumber) {
            Integer lessonNumber = lesson.getLessonNumber();
            Integer weekDay = lesson.getWeekDay();
            lessons[lessonNumber - 1][weekDay - 1] = lesson;
        }

        return lessons;
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

}
