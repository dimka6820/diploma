package com.dmma.diploma.controller.report;

import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReportController {
    private boolean flag = true;

    @Autowired
    UnsuccessfulLessonRepository unsuccessfulLessonRepository;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        List<UnsuccessfulLesson> unsuccessfulLessons = unsuccessfulLessonRepository.findAll();

        model.put("unsuccessfulLessons", unsuccessfulLessons);
        return "report/unsuccessful_lesson";
    }
}
