package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Discipline;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.repository.*;
import com.dmma.diploma.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SchedulerEdithorController {

    @Autowired
    TodoService service;

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    LessonRepository lessonRepository;

    @RequestMapping(value = "/add-lesson", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model, @RequestParam Integer week, @RequestParam Integer day, @RequestParam Integer number, @RequestParam Long classRoom) {
        model.addAttribute("lesson", new Lesson(classRoomRepository.findOne(classRoom), week, day, number));

        Map<Long, String> disciplineList = new LinkedHashMap<Long, String>();
        List<Discipline> disciplines = disciplineRepository.findAll();
        for (Discipline discipline : disciplines) {
            disciplineList.put(discipline.getId(), discipline.getName());
        }
        model.addAttribute("disciplineList", disciplineList);

        Map<Long, String> teacherList = new LinkedHashMap<Long, String>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            teacherList.put(teacher.getId(), teacher.getSurname() + " " + teacher.getName() + " " + teacher.getLastname());
        }
        model.addAttribute("teacherList", teacherList);


        return "entity_editor/scheduler_editor";
    }

    @RequestMapping(value = "/add-lesson", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Lesson lesson, BindingResult result) {
        lessonRepository.saveAndFlush(lesson);

        ClassRoom classRoom = lesson.getClassRoom();

        return "redirect:/scheduler?body="+classRoom.getBody()+"&number="+classRoom.getNumber();
    }

    @RequestMapping(value = "/delete-lesson", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam Long id, @RequestParam Long classRoomId) {
        lessonRepository.delete(id);
        ClassRoom classRoom = classRoomRepository.getOne(classRoomId);
        return "redirect:/scheduler?body="+classRoom.getBody()+"&number="+classRoom.getNumber();
    }

    @RequestMapping(value = "/update-lesson", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam Long id, ModelMap model) {
        Lesson lesson = lessonRepository.findOne(id);
        model.addAttribute("lesson", lesson);

        Map<Long, String> disciplineList = new LinkedHashMap<Long, String>();
        List<Discipline> disciplines = disciplineRepository.findAll();
        for (Discipline discipline : disciplines) {
            disciplineList.put(discipline.getId(), discipline.getName());
        }
        model.addAttribute("disciplineList", disciplineList);

        Map<Long, String> teacherList = new LinkedHashMap<Long, String>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            teacherList.put(teacher.getId(), teacher.getSurname() + " " + teacher.getName() + " " + teacher.getLastname());
        }
        model.addAttribute("teacherList", teacherList);

        return "entity_editor/scheduler_editor";
    }

    @RequestMapping(value = "/update-lesson", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Lesson lesson,
                             BindingResult result) {
        lessonRepository.saveAndFlush(lesson);

        return "redirect:/scheduler?body=\"+classRoom.getBody()+\"&number=\"+classRoom.getNumber()";
    }

}
