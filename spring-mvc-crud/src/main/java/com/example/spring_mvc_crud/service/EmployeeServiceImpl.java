package com.example.spring_mvc_crud.service;

import com.example.spring_mvc_crud.entity.Employee;
import com.example.spring_mvc_crud.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // mark the class as service implementation, enable component scanning.
public class EmployeeServiceImpl implements EmployeeService {

    // inject JpaRepository
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        // return list of employee objects
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty())
            throw new RuntimeException("Don't found this user" + id);

        return employee.get();
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {     // `save` check employee
        return employeeRepository.save(employee); // if existed, modify it
    }                                             // if not, add it
                                                  // return new employee version
    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
