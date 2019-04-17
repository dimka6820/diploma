package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.Discipline;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.repository.DisciplineRepository;
import com.dmma.diploma.repository.TeacherRepository;
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
public class DisciplineEditorController {

    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/add-discipline", method = RequestMethod.GET)
    public String showDisciplines(ModelMap model) {
        model.addAttribute("discipline", new Discipline());

        Map<Long, String> teacherList = new LinkedHashMap<Long, String>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            teacherList.put(teacher.getId(), teacher.getSurname() + " " + teacher.getName() + " " + teacher.getLastname());
        }
        model.addAttribute("teacherList", teacherList);

        return "entity_editor/discipline_editor";
    }

    @RequestMapping(value = "/add-discipline", method = RequestMethod.POST)
    public String addDiscipline(ModelMap model, @Valid Discipline discipline, BindingResult result) {
        disciplineRepository.saveAndFlush(discipline);
        return "redirect:/discipline";
    }

    @RequestMapping(value = "/remove-discipline", method = RequestMethod.GET)
    public String deleteDiscipline(@RequestParam Long id) {
        disciplineRepository.delete(id);
        return "redirect:/discipline";
    }

    @RequestMapping(value = "/update-discipline", method = RequestMethod.GET)
    public String showUpdateDiscipline(@RequestParam Long id, ModelMap model) {
        Discipline discipline = disciplineRepository.findOne(id);
        model.put("discipline", discipline);

        Map<Long, String> teacherList = new LinkedHashMap<Long, String>();
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            teacherList.put(teacher.getId(), teacher.getSurname() + " " + teacher.getName() + " " + teacher.getLastname());
        }
        model.addAttribute("teacherList", teacherList);

        return "entity_editor/discipline_editor";
    }


    @RequestMapping(value = "/update-discipline", method = RequestMethod.POST)
    public String updateDiscipline(ModelMap model, @Valid Discipline discipline, BindingResult result) {
        disciplineRepository.saveAndFlush(discipline);

        return "redirect:/discipline";
    }
}
