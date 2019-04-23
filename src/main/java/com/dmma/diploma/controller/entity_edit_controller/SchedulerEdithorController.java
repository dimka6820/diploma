package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.repository.ClassRoomRepository;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.TeacherRepository;
import com.dmma.diploma.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SchedulerEdithorController {

    @Autowired
    TodoService service;

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    LessonRepository lessonRepository;

    @RequestMapping(value = "/add-lesson", method = RequestMethod.GET)
    public String showAddLessonPage(ModelMap model, @RequestParam Integer week, @RequestParam Integer day, @RequestParam Integer number, @RequestParam Long classRoom) {
        model.addAttribute("lesson", new Lesson(classRoomRepository.findOne(classRoom), week, day, number));

        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);


        return "entity_editor/scheduler_editor";
    }

    @RequestMapping(value = "/add-lesson", method = RequestMethod.POST)
    public String addLesson(ModelMap model, @Valid Lesson lesson, BindingResult result) {
        lessonRepository.saveAndFlush(lesson);

        ClassRoom classRoom = lesson.getClassRoom();

        return "redirect:/scheduler?body="+classRoom.getBody()+"&number="+classRoom.getNumber();
    }

    @RequestMapping(value = "/delete-lesson", method = RequestMethod.GET)
    public String deleteLesson(@RequestParam Long id, @RequestParam Long classRoomId) {
        lessonRepository.delete(id);
        ClassRoom classRoom = classRoomRepository.getOne(classRoomId);
        return "redirect:/scheduler?body="+classRoom.getBody()+"&number="+classRoom.getNumber();
    }
}
