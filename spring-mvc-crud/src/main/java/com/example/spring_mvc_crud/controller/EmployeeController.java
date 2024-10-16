package com.example.spring_mvc_crud.controller;

import com.example.spring_mvc_crud.entity.Employee;
import com.example.spring_mvc_crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // mark the class as mvc controller bean.
@RequestMapping("/employees")
public class EmployeeController {

    /* trim any string input */
    @InitBinder // indicate that following method will run before binding process starts
    public void initBinder(WebDataBinder dataBinder) {
        // this method will occur with all requests for this controller
        // `WebDataBinder`: this object responsible for binding request parameters with java objects.

        // `StringTrimmerEditor`: this class responsible for remove whitespaces.
        // "true" means trim to null, if string contains only whitespaces.
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // Means for every string class, apply stringTrimmerEditor.
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /* Inject Employee Service */
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /* retrieve all employees */
    @GetMapping("/list")
    public String listAllEmployees(Model model) {
        // Retrieve employees from database
        List<Employee> employees = employeeService.findAll();

        // sort the result based on first name
        employees.sort((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));

        // Add employees to model
        model.addAttribute("employees", employees);

        // return html page that display employees
        return "employee/employee-list";
    }

    /* Show save employee form */
    @GetMapping("/saveForm")
    public String saveEmployeeForm(Model model) {
        // add employee object to model
        model.addAttribute("employee", new Employee());

        // return page that display the form
        return "employee/employee-form";
    }

    /* process save/update employee */
    @PostMapping("/save")
    public String processEmployeeForm(@Valid @ModelAttribute("employee") Employee employee,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employee/employee-form";
        }

        // save new employee to database
        employeeService.save(employee);

        // redirect to main page
        return "redirect:/employees/list";
    }

    /* show update employee form */
    @GetMapping("/updateForm")
    public String updateEmployeeForm(@RequestParam("employeeId") int id, Model model) {
        // find the user
        Employee employee = employeeService.findById(id);

        // add it to model
        model.addAttribute("employee", employee); // adding process here is so important, because fields auto filled with object values.

        // return form template
        return "employee/employee-form";
    }

    /* process delete employee */
    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        // supposed you added exception if the user didn't exist in db.

        // delete employee
        employeeService.deleteById(id);

        // return employees list page
        return "redirect:/employees/list";
    }
}
