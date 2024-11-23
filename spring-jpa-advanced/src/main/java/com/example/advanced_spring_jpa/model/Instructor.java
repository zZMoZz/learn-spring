package com.example.advanced_spring_jpa.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // mark the class as entity which map a table. add this class in component scanning.
@Table(name = "instructor")
public class Instructor {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")   // this is the foreign key column in 'Instructor' that associate between two entities.
    private InstructorDetails instructorDetails;

    // `mappedBy` make Hibernate look at "instructor" field in "course" class
    // so automatically, use the column that specified in '@JoinColumn' to bind between two tables.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    // to can add single course, setter method add full list, not one by one
    public void addCourse(Course course) {
        if (courses == null)
            courses = new ArrayList<>();

        // add course to instructor
        courses.add(course); // make instructor can see associated courses.
                             // to include them in saving process.

        // add instructor to course
        course.setInstructor(this); // this line is responsible for assign foreign key
                                    // column with specified instructor_id.
                                    // without it, associated course will saved with save instructor
                                    // but `instructor_id` field in course will carry `null`.
    }

    /* Constructors */

    public Instructor() {} // no-argument is required for jpa.

    public Instructor(String firstName, String lastName, String email) {
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

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /* To String */

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email;
    }
}
