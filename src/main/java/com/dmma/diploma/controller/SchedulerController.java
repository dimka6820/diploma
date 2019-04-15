package com.dmma.diploma.controller;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Todo;
import com.dmma.diploma.repository.*;
import com.dmma.diploma.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SchedulerController {

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        Lesson[][] lessons1 = new Lesson[8][6];
        Lesson[][] lessons2 = new Lesson[8][6];

        List<Lesson> lessonWeekNumber = lessonRepository.findByWeekNumber(1);
        for (Lesson lesson : lessonWeekNumber) {
            Integer lessonNumber = lesson.getLessonNumber();
            Integer weekDay = lesson.getWeekDay();
            lessons1[lessonNumber - 1][weekDay - 1] = lesson;
        }
        List<Lesson> lessonWeekNumber2 = lessonRepository.findByWeekNumber(2);
        for (Lesson lesson : lessonWeekNumber2) {
            Integer lessonNumber = lesson.getLessonNumber();
            Integer weekDay = lesson.getWeekDay();
            lessons2[lessonNumber - 1][weekDay - 1] = lesson;
        }

        model.put("lesson1", lessons1);
        model.put("lesson2", lessons2);

        return "scheduler/scheduler";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new Todo(0, getLoggedInUserName(model),
                "Default Desc", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam Long id) {
        lessonRepository.delete(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = service.retrieveTodo(id);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser(getLoggedInUserName(model));

        service.updateTodo(todo);

        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),
                false);
        return "redirect:/list-todos";
    }
}
