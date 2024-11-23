package com.example.advanced_spring_jpa.dao;

import com.example.advanced_spring_jpa.model.Course;
import com.example.advanced_spring_jpa.model.Instructor;
import com.example.advanced_spring_jpa.model.InstructorDetails;
import com.example.advanced_spring_jpa.model.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**** Instructor ****/

    // retrieve instructor and its details entity.
    // fetch type for course entity is LAZY. so, will not retrieved.
    @Override
    public Instructor findInstructorLazyById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    // retrieve instructor and associated entities (details, course)
    @Override
    public Instructor findInstructorEagerById(int id) {
        return entityManager.createQuery(
                "SELECT i FROM Instructor i "
                    + "JOIN FETCH i.courses "
                    + "JOIN FETCH i.instructorDetails "
                    + "WHERE i.id = :instructorId", Instructor.class)
                .setParameter("instructorId", id)
                .getSingleResult();
    }

    // save instructor and associated entities (course, details).
    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    // remove instructor and details entity only.
    // delete operation for course entity not included in cascade operations.
    @Override
    @Transactional
    public void removeInstructorById(int id) {
        // firstly, break relation between instructor and its courses
        // otherwise; removing process will not success because this instructor already in use from other tables.

        // get specified instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

        // get associated courses
        List<Course> courses = instructor.getCourses();

        // break relation between instructor and it's courses
        for (Course course : courses)
            course.setInstructor(null);

        // remove instructor
        entityManager.remove(instructor);
    }

    // update instructor and associated entities (course, details).
    // !!!!!!!!!!!!!!!!! How manage update for other related entities !!!!!!!!!!!!!!!!!.
    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }


    /**** Instructor Details ****/

    // retrieve details and associated entities (instructor).
    @Override
    public InstructorDetails findInstructorDetailsById(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }

    // remove details only. remove operation for instructor doesn't included in cascade operations.
    @Override
    @Transactional
    public void removeInstructorDetailsById(int id) {
        // break relation between details and instructor.
        // because can't delete a record in use by another table.
        InstructorDetails instructorDetails = findInstructorDetailsById(id);
        instructorDetails.getInstructor().setInstructorDetails(null);

        entityManager.remove(instructorDetails);
    }

    // save details and associated entities (instructor).
    @Override
    @Transactional
    public void saveInstructorDetails(InstructorDetails instructorDetails) {
        entityManager.persist(instructorDetails);
    }

    /**** Course ****/

    // retrieve course only.
    // (Instructor, Student, Review) entities are LAZY.
    @Override
    public Course findCourseLazyById(int id) {
        return entityManager.find(Course.class, id);
    }

    // retrieve course and associated entities (Instructor, Student, Review).
    @Override
    public Course findCourseEagerById(int id) {
        return entityManager.createQuery(
                "SELECT c FROM course c " +
                   "JOIN FETCH c.instructor " +
                   "LEFT JOIN FETCH c.review" +
                   "LEFT JOIN FETCH c.student" +
                   "WHERE c.id = :courseId", Course.class)
                   .setParameter("courseId", id)
                   .getSingleResult();
    }

    // retrieve course with its reviews without other lazy entities.
    @Override
    public Course findCourseAndReviewByCourseId(int id) {
        return entityManager.createQuery(
                 "SELECT c FROM course c " +
                    "JOIN FETCH c.review " +
                    "WHERE c.id = :courseId", Course.class)
                    .setParameter("courseId", id)
                    .getSingleResult();
    }

    // retrieve course with its students without other lazy entities.
    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        return entityManager.createQuery(
                "SELECT c FROM course c " +
                "JOIN FETCH c.student " +
                "WHERE c.id = :courseId", Course.class)
                .setParameter("courseId", id)
                .getSingleResult();
    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        return entityManager.createQuery(
                "FROM Course WHERE instructor.id = :instructorId", Course.class)
                .setParameter("instructorId", id)
                .getResultList();
    }

    // remove course and associated entities (review).
    @Override
    @Transactional
    public void removeCourseById(int id) {
        // break the relation with students
        
        entityManager.remove(findCourseLazyById(id));
    }

    // save course and associated entities if exist
    // (review, student, instructor)
    @Override
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    // update course and associated entities if exist
    // (review, student, instructor)
    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    /**** Course ****/

    // find student only. course is lazy.
    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    //
    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        return entityManager.createQuery(
                "SELECT s FROM student s " +
                "JOIN FETCH s.course " +
                "WHERE s.id = :studentId", Student.class)
                .setParameter("studentId", id)
                .getSingleResult();
    }

    // remove student only. course delete cascade operation not selected
    @Override
    public void removeStudentById(int id) {
        entityManager.remove(findStudentById(id));
    }

    // save student and associated entities (course).
    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    // update student and associated entities (course).
    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
}
