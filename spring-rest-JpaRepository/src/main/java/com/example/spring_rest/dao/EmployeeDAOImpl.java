package com.example.spring_rest.dao;

import com.example.spring_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // it marks the class as DAO implementation
public class EmployeeDAOImpl implements EmployeeDAO {
    // We will implement DAO using JPA's EntityManager.
    // Hibernate who implement all EntityManger methods implicitly.
    // Spring boot uses Hibernate as implementation to JPA, so it will injected automatically.
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee); // if id = 0, save, otherwise update
    }                                         // return employee with updated data

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("FROM Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
