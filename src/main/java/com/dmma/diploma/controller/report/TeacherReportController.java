package com.dmma.diploma.controller.report;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.TeacherRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.dmma.diploma.service.UnsuccessfulLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TeacherReportController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UnsuccessfulLessonRepository unsuccessfulLessonRepository;
    @Autowired
    LessonRepository lessonRepository;

    @RequestMapping(value = "/teacherList", method = RequestMethod.GET)
    public String showClassRoom(ModelMap model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.put("teachers", teachers);

        return "teacher/teacher_list";
    }

    @RequestMapping(value = "/teacherReport", method = RequestMethod.GET)
    public String showTeacherReport(ModelMap model, @RequestParam Long teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);

        List<Lesson> lessons = lessonRepository.findByTeacher(teacher);
        List<UnsuccessfulLesson> unsuccessfulLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            UnsuccessfulLesson unsuccessfulLesson = unsuccessfulLessonRepository.findByLesson(lesson);
            if(unsuccessfulLesson != null){
                unsuccessfulLessons.add(unsuccessfulLesson);
            }
        }

        model.put("unsuccessfulLessons", unsuccessfulLessons);
        model.put("teacherId", teacherId);

        return "report/unsuccessful_lesson";
    }

}
