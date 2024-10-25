package com.example.spring_security_mvc.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // indicate this class contains beans definitions
public class LoginController {

    // redirect to this endpoint is the user doesn't authenticate.
    // submitted endpoint will be handled by spring security.
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/access-denied")
    public String customAccessDeniedPage() {
        return "access-denied";
    }

}
