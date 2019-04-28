package com.dmma.diploma.controller;

import com.dmma.diploma.model.security.User;
import com.dmma.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String showUsers(ModelMap model) {
        List<User> users = userRepository.findAll();
        model.put("users", users);

        return "user/user";
    }
}
