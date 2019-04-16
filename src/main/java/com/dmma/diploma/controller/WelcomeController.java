package com.dmma.diploma.controller;

import com.dmma.diploma.model.security.Role;
import com.dmma.diploma.model.security.User;
import com.dmma.diploma.opencv.FindMoves;
import com.dmma.diploma.repository.UserRepository;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
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
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

@Controller
public class WelcomeController {
    private boolean flag = true;
    VideoCapture capture;
    ScheduledExecutorService timer;

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
    private UserRepository userRepository;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        User user = new User();
        user.setActive(true);
        user.setPassword("123");
        user.setUsername("u");
        user.setRoles(Collections.singleton(Role.ROLE_USER));
//        userRepository.save(user);


//        if (flag) {
//            flag = false;
        capture = new VideoCapture();
        capture.open(0);
        System.out.println(capture.toString());
        capture.open(2);
//            if (capture.isOpened()) {
        FindMoves findMoves = new FindMoves(capture);
//                Runnable frameGrabber = () -> {
        Mat frame = findMoves.findMoves();
//                };
//
//                timer = Executors.newSingleThreadScheduledExecutor();
//                timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
//
//            } else {
//                System.err.println("Failed to open the camera connection...");
//            }
//        } else {
//            flag = true;
//            if (timer != null && !timer.isShutdown()) {
//                try {
//                    timer.shutdown();
//                    timer.awaitTermination(33, TimeUnit.MILLISECONDS);
//                } catch (InterruptedException e) {
//                    System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
//                }
//            }
//
//            if (capture.isOpened()) {
//                capture.release();
//            }
//        }
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
