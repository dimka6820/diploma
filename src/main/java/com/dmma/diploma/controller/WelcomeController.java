package com.dmma.diploma.controller;

import com.dmma.diploma.model.Lesson;
import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.pdf_builder.ReportPdfDocumentBuilder;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.dmma.diploma.repository.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class WelcomeController {
    private boolean flag = true;

    @Autowired
    private UnsuccessfulLessonRepository unsuccessfulLessonRepository;
    @Autowired
    private ReportPdfDocumentBuilder reportPdfDocumentBuilder;
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

    @RequestMapping(value = "/qwert", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {
//        try {
//            Lesson lesson = lessonRepository.findAll().get(0);
//            Document newDocument = reportPdfDocumentBuilder.createNewDocument(lesson);
//            Document listHeader = reportPdfDocumentBuilder.createListHeader(lesson, newDocument);
//            reportPdfDocumentBuilder.finishDocument(listHeader);
//
//        } catch (IOException | DocumentException e) {
//            System.out.println(e);
//        }

        List<Lesson> cities = lessonRepository.findAll();

        ByteArrayInputStream bis = reportPdfDocumentBuilder.citiesReport(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

//    @RequestMapping(value = "/qwert", method = RequestMethod.GET)
//    public String qwerty(ModelMap model) {
//        try {
//            Lesson lesson = lessonRepository.findAll().get(0);
//            Document newDocument = reportPdfDocumentBuilder.createNewDocument(lesson);
//            Document listHeader = reportPdfDocumentBuilder.createListHeader(lesson, newDocument);
//            reportPdfDocumentBuilder.finishDocument(listHeader);
//
//        } catch (IOException | DocumentException e) {
//            System.out.println(e);
//        }
//
//        return "welcome";
//    }

    private String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

}
