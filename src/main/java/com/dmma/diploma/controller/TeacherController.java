package com.dmma.diploma.controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.Teacher;
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
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String showClassRoom(ModelMap model) {
        List<Teacher> teachers = teacherRepository.findAll();
        model.put("teachers", teachers);

        return "teacher/teacher";
    }
}
