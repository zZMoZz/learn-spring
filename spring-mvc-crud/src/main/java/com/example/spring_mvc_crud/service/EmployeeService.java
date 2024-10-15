package com.example.spring_mvc_crud.service;

import com.example.spring_mvc_crud.entity.Employee;

import java.util.List;

// Why we use service in existence of JpaRepository?
// - Because business logic code of employee of course use data from multiple tables
// - not only Employee repository so, we create the service to include the all logic that related to Employee in one place.
// - Even you use Employee repository only right now in your app. Because if you need service in future, you will
// - controllers instead of using repository make it use service. so what all of this headache?
public interface EmployeeService {
    // Declare all needed methods

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
