package com.dmma.diploma.controller;

import com.dmma.diploma.model.UnsuccessfulLesson;
import com.dmma.diploma.repository.LessonRepository;
import com.dmma.diploma.repository.UnsuccessfulLessonRepository;
import com.dmma.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.List;

@Controller
public class WelcomeController {
    private boolean flag = true;

    @RequestMapping("/cover={bookID}")
    public ResponseEntity<byte[]> testphoto(
            @PathVariable(value = "bookID") String bookId) throws IOException {
        ByteArrayOutputStream out = null;
        InputStream input = null;
        try {
            out = new ByteArrayOutputStream();
            input = new BufferedInputStream(new FileInputStream("snapshots/2019-04-07_23-07-08.jpg"));
            int data = 0;
            while ((data = input.read()) != -1) {
                out.write(data);
            }
        } finally {
            if (null != input) {
                input.close();
            }
            if (null != out) {
                out.close();
            }
        }
        byte[] bytes = out.toByteArray();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }

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

        List<UnsuccessfulLesson> byLesson = unsuccessfulLessonRepository.findByLesson(lessonRepository.findOne(115L));
        System.out.println(byLesson);

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
