package com.example.spring_security.service;


import com.example.spring_security.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // Get employee methods
    List<Employee> findAll();
    Employee findById(int id);

    // add & update methods
    Employee save(Employee employee);

    // deletion methods
    void deleteById(int id);
}
