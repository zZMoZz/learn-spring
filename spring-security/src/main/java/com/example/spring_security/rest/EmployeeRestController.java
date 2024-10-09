package com.example.spring_security.rest;

import com.example.spring_security.entity.Employee;
import com.example.spring_security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // Inject Employee service
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Get specific employee
    @GetMapping("/employees/{employeeId}")
    public Employee findEmployee(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    // Add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0); // if user assign id field, the algorithm will update not add new one.
        return employeeService.save(employee);
    }

    // Update an employee
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
        if (employeeService.findById(employeeId) == null)
            throw new RuntimeException("Can't find this user - " + employeeId);

        employee.setId(employeeId); // to make user can't change id, even he passed it.
        return employeeService.save(employee);
    }

    // Delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        // find user
        if (employeeService.findById(employeeId) == null)
            throw new RuntimeException("Can't find this user - " + employeeId);

        // delete user
        employeeService.deleteById(employeeId);

        return "Delete this user successfully - " + employeeId;
    }
}
