package com.example.spring_security_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/leaders")
    public String showLeaders() {
        return "leader-page";
    }

    @GetMapping("/systems")
    public String showSystems() {
        return "system-page";
    }
}
