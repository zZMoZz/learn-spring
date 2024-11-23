package com.example.advanced_spring_jpa.dao;

import com.example.advanced_spring_jpa.model.*;

import java.util.List;

public interface AppDao {
    Instructor findInstructorLazyById(int id);
    Instructor findInstructorEagerById(int id); // implementation in page 107
    void removeInstructorById(int id);
    void saveInstructor(Instructor instructor);
    void updateInstructor(Instructor instructor);

    InstructorDetails findInstructorDetailsById(int id);
    void removeInstructorDetailsById(int id);
    void saveInstructorDetails(InstructorDetails instructorDetails);

    Course findCourseLazyById(int id);
    Course findCourseEagerById(int id);
    Course findCourseAndReviewByCourseId(int id);
    Course findCourseAndStudentByCourseId(int id);
    List<Course> findCourseByInstructorId(int id); // implementation in page 104
    void removeCourseById(int id);
    void saveCourse(Course course);
    void updateCourse(Course course);

    Student findStudentById(int id);
    Student findStudentAndCoursesByStudentId(int id);
    void removeStudentById(int id);
    void saveStudent(Student student);
    void updateStudent(Student student);



    // We can access reviews only through courses.
    // So, I will not add any methods for it.


}
