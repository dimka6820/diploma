package com.dmma.diploma.controller;

import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
