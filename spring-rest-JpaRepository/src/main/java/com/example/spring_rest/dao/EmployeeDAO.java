package com.example.spring_rest.dao;


import com.example.spring_rest.entity.Employee;
import java.util.List;

// This interface includes methods declaration to deal with db
// It separates between business logic and data access logic.
public interface EmployeeDAO {
    // Declare DAO methods
    Employee save(Employee employee); // update & create
    List<Employee> findAll();
    Employee findById(int id);
    void deleteById(int id);
}
