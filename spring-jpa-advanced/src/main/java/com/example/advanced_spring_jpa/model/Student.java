package com.example.advanced_spring_jpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity // mark the class as entity which means map a table in db. and add the class into component scanning.
public class Student {

    /* Fields */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /* Relations */

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "course_student",                        // this annotation tells Hibernate
               joinColumns = @JoinColumn(name = "student_id"), // look at `student_id` column in `course_student` table
               inverseJoinColumns = @JoinColumn(name = "course_id")) // and for inverse side look at `course_id` column
    private List<Course> courses;

    /* Constructors */

    public Student() {}

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /* Getters & Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /* Tools */

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
