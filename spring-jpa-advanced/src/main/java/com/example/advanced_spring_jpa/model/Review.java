package com.example.advanced_spring_jpa.model;

import jakarta.persistence.*;

@Entity // mark the class as entity which maps to a table in db. also include the class in component scanning.
@Table(name = "review")
public class Review {

    /* Fields */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // means manage by db
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    /* Relations */

    // there is a unidirectional one-to-many relationship between course-to-review.
    // review doesn't know about this relation, so we can't manage courses through review.

    /* Constructors */

    public Review() {}

    public Review(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    /* Getters & Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /* FuncTools */

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
