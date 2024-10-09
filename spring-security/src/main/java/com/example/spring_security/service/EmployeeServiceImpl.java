package com.example.spring_security.service;

import com.example.spring_security.dao.EmployeeRepository;
import com.example.spring_security.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // mark the class as service implementation & enable component scanning
public class EmployeeServiceImpl implements EmployeeService {
    /* Inject JpaRepository */

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /* Methods Implementation */

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty())
            throw new RuntimeException("Don't found this user - " + id);

        return employee.get();
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {     //if not exist, add one
        return employeeRepository.save(employee); // if exists, modify it
    }                                             // return the new version

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
