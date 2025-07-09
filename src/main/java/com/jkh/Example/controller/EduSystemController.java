package com.jkh.Example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EduSystemController {
    @GetMapping("/edusystem")
    public String index() {
        return "edu_index";
    }
}