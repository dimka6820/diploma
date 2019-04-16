package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.ClassRoom;
import com.dmma.diploma.model.Todo;
import com.dmma.diploma.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ClassRoomEditorController {

    @Autowired
    ClassRoomRepository classRoomRepository;

    @RequestMapping(value = "/add-classroom", method = RequestMethod.GET)
    public String showClassRoom(ModelMap model) {
        model.addAttribute("classroom", new ClassRoom());

        return "entity_editor/class_room_editor";
    }

    @RequestMapping(value = "/add-classroom", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid ClassRoom classRoom, BindingResult result) {
        classRoomRepository.saveAndFlush(classRoom);
        return "redirect:/classroom";
    }

    @RequestMapping(value = "/remove-classroom", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam Long id) {
        classRoomRepository.delete(id);
        return "redirect:/classroom";
    }
}
