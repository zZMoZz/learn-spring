package com.example.spring_mvc.controller;

import com.example.spring_mvc.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // mark the class as MVC controller. It's methods return views.
public class DemoController {
    // catch properties
    @Value("${countries}")
    private List<String> countries;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // It is a pre-processor method for all request for this controller
    @InitBinder // indicate that this method will be called before binding process starts.
    public void initBinder(WebDataBinder dataBinder) {
        // `WebDataBinder` this object responsible for binding request parameters with java objects.
        // this class responsible for remove whitespaces
        // "true" means trim to null, if string contains only white spaces.
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // means for every string object, apply stringTrimmerEditor.
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // show form page
    @GetMapping("/studentForm")
    public String studentFormShow(Model model) {
        // add student object as a parameter to can bind with form inputs.
        // this parameter can be access automatically by thymeleaf as it in model.
        model.addAttribute("student", new Student());
        model.addAttribute("countries", countries);
        model.addAttribute("os", systems);

        return "student-form-show";
    }

    // show submission page
    @PostMapping("/studentForm")
    public String studentFormProcess(
            // `Valid` tell spring to validate this student object.
            @Valid @ModelAttribute Student student,
            // hold result errors of validation process
            BindingResult bindingResult, Model model) {

        // logic that handle validation process
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", countries);
            model.addAttribute("os", systems);
            return "student-form-show";
        } else
            return "student-form-process";
    }
}
