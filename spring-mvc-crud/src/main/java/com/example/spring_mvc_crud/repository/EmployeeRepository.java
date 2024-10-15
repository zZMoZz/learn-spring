package com.example.spring_mvc_crud.repository;

import com.example.spring_mvc_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// this interface inherit all methods that JpaRepository provide.
// only add entity and the data type of primary key column.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // No need to further code ...
    // no you have all JpaRepository methods applied to Employee entity.
    // visit documentation, If you don't know names of its methods.
}
