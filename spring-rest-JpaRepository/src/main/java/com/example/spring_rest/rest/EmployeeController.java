package com.example.spring_rest.rest;

import com.example.spring_rest.entity.Employee;
import com.example.spring_rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.spring_rest.exception.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    // Inject service
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // endpoint: get all employees
    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    // endpoint: get specific employee by id
    @GetMapping("/employees/{employeeId}")
    public Employee getById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null)
            throw new EmployeeNotFoundException(employeeId);

        return employee;
    }

    // endpoint: add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) { // client will send data, so use `@RequestBody` to catch it
        // user may pass id value, that make save work as update not add new employee
        // so explicitly after catching the passed data, do id = 0
        employee.setId(0);
        return employeeService.save(employee);  // return the Employee with new data, in this case only id who will added in returned data
    }

    // endpoint : update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        if (employee.getId() == 0)
            throw new BadRequestException("Can't find employee without id");

        else if (employeeService.findById(employee.getId()) == null)
            throw new EmployeeNotFoundException(employee.getId());

            return employeeService.save(employee);
    }

    // endpoint: delete
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        if (employeeService.findById(employeeId) == null)
            throw new EmployeeNotFoundException(employeeId);

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}

