package com.dmma.diploma.controller;

import com.dmma.diploma.model.Discipline;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.repository.DisciplineRepository;
import com.dmma.diploma.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DisciplineController {

    @Autowired
    DisciplineRepository disciplineRepository;

    @RequestMapping(value = "/discipline", method = RequestMethod.GET)
    public String showDiscipline(ModelMap model) {
        List<Discipline> disciplines = disciplineRepository.findAll();
        model.put("disciplines", disciplines);

        return "discipline/discipline";
    }
}
