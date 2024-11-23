package com.example.advanced_spring_jpa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // mark the class as entity which maps to table. add this class into component scanning.
@Table(name = "course")
public class Course {

    /* Fields */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    /* Relations */

    // Define many-to-one bidirectional relation between courses and instructor.
    // In bidirectional relation, "many" side must be the owning side.
    // So, it must contain `@JoinColumn`.
    // In unidirectional, when we putted `@JoinColumn` in "one" side, this was a special case.
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id") // determine foreign key column.
    private Instructor instructor;

    // Define unidirectional one-to-many relation with review entity.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // in this relation, `@JoinColumn` behavior is unique
    private List<Review> reviews;   // where there is no back reference from "many" side to "one" side
                                    // because we don't want the review entity to know the relation.
                                    // So, we can't put `@JoinColumn` in review entity.
                                    // So, this approach of `@JoinColumn` is special case to `@OneToMany` unidirectional.
                                    // in this case, this line tells Hibernate to use `course_id` column that in Review to manage join process of relation.

    // To can add new review through course
    public void addReview(Review review) {
        if (reviews == null)
            reviews = new ArrayList<>();
        reviews.add(review); // Yes this line is enough to add a value to `course_id` column unlike bidirectional relation.
    }                        // because `@JoinColumn` is declared in Course entity.


    // Define bidirectional many-to-many relation with student entity.
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "course_student",
               joinColumns = @JoinColumn(name = "course_id"),
               inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    /* Constructors */

    public Course() {} // no-argument constructor is required for jpa

    public Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }

    public Course(String title) {
        this.title = title;
    }

    /* Getters & Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /* Tools */

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title;
    }
}
