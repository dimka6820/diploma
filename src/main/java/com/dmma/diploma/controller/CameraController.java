package com.dmma.diploma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CameraController {

    @RequestMapping(value = "/camera", method = RequestMethod.GET)
    public String showCameras(ModelMap model) {

        return "camera/camera";
    }
}
