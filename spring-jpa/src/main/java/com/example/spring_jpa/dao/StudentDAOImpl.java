package com.example.spring_jpa.dao;

import com.example.spring_jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // it tells spring that this class is a DAO component
public class StudentDAOImpl implements StudentDAO {
    // we will implement student DAO using JPA's EntityManager, so must inject it
    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // handle transaction process automatically instead of manually
    public void save(Student student) {
        entityManager.persist(student); // save the object to database
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAllStudents() {
        return entityManager.createQuery("FROM Student", Student.class)
                .getResultList();
    }

    @Override
    public List<Student> findByFirstNameOrLastName(String firstName, String lastName) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student WHERE lastName=:ls OR firstName=:fs", Student.class);

        // fill placeholders
        query.setParameter("ls", lastName);
        query.setParameter("fs", firstName);

        // return founded objects
        return query.getResultList();
    }

    @Override
    @Transactional // because we modify the database
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int updateLastName(int id, String newLastName) {
        // create query
        Query query = entityManager.createQuery( // we don't use `Student.class` as a second argument. because we use
                "UPDATE Student s SET s.lastName=:ls WHERE s.id=:i"); // it with `SELECT` queries only.

        // fill placeholder
        query.setParameter("ls", newLastName);
        query.setParameter("i", id);

        // apply updates
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteByLastName(String lastName) {
        // this approach not good, use setParameter. it's better
        return entityManager.createQuery(
                "DELETE FROM Student WHERE lastName='" + lastName + "'")
                .executeUpdate();

    }


}
