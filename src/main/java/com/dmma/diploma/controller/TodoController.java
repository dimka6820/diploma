package com.dmma.diploma.controller;

import com.dmma.diploma.model.*;
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
import java.util.Arrays;
import java.util.Date;

@Controller
public class TodoController {

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
//		String name = getLoggedInUserName(model);
//		model.put("todos", service.retrieveTodos(name));

//        generateTestData();
        model.put("lessons", lessonRepository.findAll());
//        System.out.println("1");
//        System.out.println("2");
//        classRoomRepository.save(classRoom);
//        System.out.println("3");
        return "list-todos";
    }

    private void generateTestData() {
        ClassRoom classRoom = new ClassRoom(201, "new");
        classRoomRepository.saveAndFlush(classRoom);

        Teacher teacher = new Teacher();
        Discipline discipline = new Discipline();
        discipline.setName("qwerty");
        teacher.setName("Alex");
        teacher.setNumber("123");
        teacher.setSurname("we");
        teacher.setLastname("wee");
        teacherRepository.saveAndFlush(teacher);
        disciplineRepository.saveAndFlush(discipline);
        teacher.setDisciplines(Arrays.asList(discipline));
        discipline.setTeachers(Arrays.asList(teacher));

        Group ifst31 = new Group("IFST31");
        groupRepository.saveAndFlush(ifst31);


        lessonRepository.saveAndFlush(
                new Lesson(
                        classRoom,
                        teacher,
                        discipline,
                        ifst31,
                        1,
                        1,
                        1,
                        false,
                        false
                )
        );
        lessonRepository.saveAndFlush(
                new Lesson(
                        classRoom,
                        teacher,
                        discipline,
                        ifst31,
                        1,
                        3,
                        1,
                        false,
                        false
                )
        );
        lessonRepository.saveAndFlush(
                new Lesson(
                        classRoom,
                        teacher,
                        discipline,
                        ifst31,
                        1,
                        2,
                        1,
                        false,
                        false
                )
        );

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
    public String deleteTodo(@RequestParam int id) {

        if (id == 1)
            throw new RuntimeException("Something went wrong");

        service.deleteTodo(id);
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
