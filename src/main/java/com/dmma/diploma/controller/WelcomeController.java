package com.dmma.diploma.controller;

import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.dmma.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WelcomeController {
    private boolean flag = true;

    @Autowired
    UnsuccessfulLessonRepository unsuccessfulLessonRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LessonRepository lessonRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        List<UnsuccessfulLesson> all = unsuccessfulLessonRepository.findAll();
        System.out.println(all);

        model.put("all", all);
        model.put("name", getLoggedinUserName());
        return "welcome";
    }

    private String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

}
