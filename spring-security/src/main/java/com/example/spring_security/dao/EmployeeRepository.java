package com.example.spring_security.dao;

import com.example.spring_security.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // no need to write any code.
    // Now Employee entity has all JpaRepository methods for free.
}
