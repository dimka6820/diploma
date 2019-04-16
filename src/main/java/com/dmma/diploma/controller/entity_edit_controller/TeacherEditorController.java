package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Teacher;
import com.dmma.diploma.model.Todo;
import com.dmma.diploma.repository.ClassRoomRepository;
import com.dmma.diploma.repository.TeacherRepository;
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
public class TeacherEditorController {

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/add-teacher", method = RequestMethod.GET)
    public String showTeachers(ModelMap model) {
        model.addAttribute("teacher", new Teacher());

        return "entity_editor/teacher_editor";
    }

    @RequestMapping(value = "/add-teacher", method = RequestMethod.POST)
    public String addTeacher(ModelMap model, @Valid Teacher teacher, BindingResult result) {
        teacherRepository.saveAndFlush(teacher);
        return "redirect:/teacher";
    }

    @RequestMapping(value = "/remove-teacher", method = RequestMethod.GET)
    public String deleteTeacher(@RequestParam Long id) {
        teacherRepository.delete(id);
        return "redirect:/teacher";
    }

    @RequestMapping(value = "/update-teacher", method = RequestMethod.GET)
    public String showUpdateTeacher(@RequestParam Long id, ModelMap model) {
        Teacher teacher = teacherRepository.findOne(id);
        model.put("teacher", teacher);
        return "entity_editor/teacher_editor";
    }


    @RequestMapping(value = "/update-teacher", method = RequestMethod.POST)
    public String updateTeacher(ModelMap model, @Valid Teacher teacher, BindingResult result) {
        teacherRepository.saveAndFlush(teacher);

        return "redirect:/teacher";
    }
}
