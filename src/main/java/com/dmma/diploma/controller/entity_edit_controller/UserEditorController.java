package com.dmma.diploma.controller.entity_edit_controller;

import com.dmma.diploma.model.security.Role;
import com.dmma.diploma.model.security.User;
import com.dmma.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserEditorController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String showUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.getAllRoles());

        return "entity_editor/user_editor";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(ModelMap model, @Valid User user, BindingResult result) {
        userRepository.saveAndFlush(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/remove-user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Long id) {
        userRepository.delete(id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.GET)
    public String showUpdateUser(@RequestParam Long id, ModelMap model) {
        User user = userRepository.findOne(id);
        model.put("user", user);
        model.addAttribute("roles", Role.getAllRoles());

        return "entity_editor/user_editor";
    }


    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
    public String updateUser(ModelMap model, @Valid User user, BindingResult result) {
        userRepository.saveAndFlush(user);
        return "redirect:/user";
    }

}
