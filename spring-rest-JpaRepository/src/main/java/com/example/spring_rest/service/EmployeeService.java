package com.example.spring_rest.service;

import com.example.spring_rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // Declare service methods
    Employee save(Employee employee);
    List<Employee> findAll();
    Employee findById(int id);
    void deleteById(int id);
}
