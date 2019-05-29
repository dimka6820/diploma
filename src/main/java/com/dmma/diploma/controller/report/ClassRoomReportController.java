package com.dmma.diploma.controller.report;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.ClassRoomRepository;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.TeacherRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ClassRoomReportController {

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    UnsuccessfulLessonRepository unsuccessfulLessonRepository;
    @Autowired
    LessonRepository lessonRepository;

    @RequestMapping(value = "/classroomList", method = RequestMethod.GET)
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

        return "scheduler/classroom_list";
    }

    @RequestMapping(value = "/classroomReport", method = RequestMethod.GET)
    public String showTeacherReport(ModelMap model, @RequestParam Long classroomId) {
        ClassRoom classRoom = classRoomRepository.findOne(classroomId);

        List<Lesson> lessons = lessonRepository.findByClassRoom(classRoom);

        List<UnsuccessfulLesson> unsuccessfulLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            UnsuccessfulLesson unsuccessfulLesson = unsuccessfulLessonRepository.findByLesson(lesson);
            if(unsuccessfulLesson != null){
                unsuccessfulLessons.add(unsuccessfulLesson);
            }
        }

        model.put("unsuccessfulLessons", unsuccessfulLessons);

        return "report/unsuccessful_lesson";
    }

}
