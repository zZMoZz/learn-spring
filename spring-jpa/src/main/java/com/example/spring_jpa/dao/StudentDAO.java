package com.example.spring_jpa.dao;

import com.example.spring_jpa.entity.Student;

import java.util.List;

// This is an interface layer separates between business logic and data access logic.
public interface StudentDAO {
    void save(Student student);

    Student findById(int id);
    List<Student> findAllStudents();
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    void update(Student student);
    int updateLastName(int id, String newLastName);

    void delete(int id);
    int deleteByLastName(String lastName);
}
