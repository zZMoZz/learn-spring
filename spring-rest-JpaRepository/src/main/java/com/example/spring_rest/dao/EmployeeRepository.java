package com.example.spring_rest.dao;

import com.example.spring_rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="members") // change default resource name: '/employees' -> '/members'
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... no need to write any code.
    // and you can use all methods JpaRepository provides.
}
